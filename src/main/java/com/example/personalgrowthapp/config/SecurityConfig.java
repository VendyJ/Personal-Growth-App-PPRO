package com.example.personalgrowthapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Vypnutí CSRF ochrany
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())) // Povolení H2 konzole ve frame
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Povolit přístup k H2 konzoli bez přihlášení
                        .anyRequest().permitAll() // Ostatní požadavky vyžadují přihlášení
                )
                .formLogin(Customizer.withDefaults()); // Použití výchozí přihlašovací stránky

        return http.build();
    }
}
