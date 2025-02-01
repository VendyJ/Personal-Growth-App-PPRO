package com.example.personalgrowthapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Konfigurace Spring Security - Autentizace vypnuta, aplikace je zcela otevřená.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Konfigurace BCrypt heslovacího algoritmu.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Konfigurace AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Hlavní konfigurace bezpečnosti aplikace - **všechny stránky jsou veřejné**.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Vypnutí CSRF ochrany
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // ✅ Každý může přistupovat ke všemu
                )
                .formLogin(login -> login.disable()) // ❌ Přihlašování zakázáno
                .logout(logout -> logout.disable()); // ❌ Odhlášení zakázáno

        return http.build();
    }
}
