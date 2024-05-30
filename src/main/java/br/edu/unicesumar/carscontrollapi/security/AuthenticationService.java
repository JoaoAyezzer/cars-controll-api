package br.edu.unicesumar.carscontrollapi.security;


import br.edu.unicesumar.carscontrollapi.exceptions.AuthenticationException;
import br.edu.unicesumar.carscontrollapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));
        } catch (DisabledException e) {
            throw new AuthenticationException("Usuário inativo!");
        } catch (InternalAuthenticationServiceException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Senha inválida!");
        } catch (Exception e) {
            throw new AuthenticationException("Houve um erro não identificado");
        }

        var user = userRepository.findByEmail(request.getUsername()).orElseThrow();
        var token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }

    public AuthenticationResponse validate(String token) {
        final String email;

        email = jwtService.extractUserName(token);

        if (email != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            if(jwtService.isTokenValid(token, userDetails)){
                var user = userRepository.findByEmail(email).orElseThrow();
                return AuthenticationResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .token(token)
                        .build();
            }
        }

        return AuthenticationResponse.builder().build();
    }

}
