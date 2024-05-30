package br.edu.unicesumar.carscontrollapi.resource;

import br.edu.unicesumar.carscontrollapi.dto.Forgot;
import br.edu.unicesumar.carscontrollapi.dto.NewPassWithForgotCode;
import br.edu.unicesumar.carscontrollapi.security.AuthenticationRequest;
import br.edu.unicesumar.carscontrollapi.security.AuthenticationResponse;
import br.edu.unicesumar.carscontrollapi.security.AuthenticationService;
import br.edu.unicesumar.carscontrollapi.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthResource {

    private final AuthenticationService service;
    public static final String SESSION_TOKEN_COOKIE_NAME = "session-token";
    private final UserService userService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var session = service.authenticate(request);
        int maxAge = Boolean.TRUE.equals(request.getRemember()) ? 60 * 60 * 24 * 360 * 69 : -1;
        var cookie = ResponseCookie.from(SESSION_TOKEN_COOKIE_NAME, session.getToken())
                .httpOnly(true)
                .maxAge(maxAge)
                .path("/")
                .build()
                .toString();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie).body(session);
    }

    @PostMapping(value = "forgot")
    public ResponseEntity<Void> forgotPassword(@RequestBody Forgot forgot){
        userService.forgotPassword(forgot);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(value = "forgot/password")
    public ResponseEntity<Void> createNewPassword(@RequestBody NewPassWithForgotCode dto){
        userService.createNewPassword(dto);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "forgot/validate")
    public ResponseEntity<Void> validateForgotCode(@RequestParam(value = "code") String forgotCode){
        userService.forgotCodeValidate(forgotCode);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "validate")
    public ResponseEntity<AuthenticationResponse> validate(@NonNull HttpServletRequest request) {
        String jwt = null;
        final Cookie[] cookies = request.getCookies();
        String authHeader = request.getHeader("Authorization");

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SESSION_TOKEN_COOKIE_NAME)) {
                    jwt = cookie.getValue();
                }
            }
        } else if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
        }

        var session = service.validate(jwt);

        return ResponseEntity.ok().body(session);
    }

    @PostMapping(value = "logout")
    public ResponseEntity<Void> logout(@NonNull HttpServletRequest request) {
        var cookie = ResponseCookie.from(SESSION_TOKEN_COOKIE_NAME, "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build()
                .toString();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie).build();
    }


}
