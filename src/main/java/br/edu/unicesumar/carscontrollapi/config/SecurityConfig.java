package br.edu.unicesumar.carscontrollapi.config;

import br.edu.unicesumar.carscontrollapi.security.JwtAuthenticationFilter;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] PUBLIC_MATCHERS = {
            "/login/**",
            "/logout/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/doc"

    };


    @PostConstruct
    public void init() {
        jwtAuthenticationFilter.setPublicMatchers(PUBLIC_MATCHERS);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                authorize ->
                        authorize
                                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                                .requestMatchers(HttpMethod.POST, "login/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "logout/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/doc").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(
                        sessionManagementConfigurer ->
                                sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
