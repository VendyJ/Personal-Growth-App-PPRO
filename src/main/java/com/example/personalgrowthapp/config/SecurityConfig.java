package com.example.personalgrowthapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Konfigurační třída pro zabezpečení aplikace pomocí Spring Security.
 * Tato třída definuje pravidla pro přístup k různým částem aplikace.
 */
@Configuration // Označuje tuto třídu jako Spring konfigurační třídu
@EnableWebSecurity // Povolení Spring Security
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Definuje filtr zabezpečení, který určuje, jak budou požadavky na aplikaci ověřovány.
     *
     * @param http objekt HttpSecurity pro konfiguraci bezpečnostních pravidel
     * @return SecurityFilterChain konfigurace filtru zabezpečení
     * @throws Exception pokud nastavení zabezpečení selže
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Vypnutí CSRF ochrany pro usnadnění práce v lokálním vývoji (pro produkční prostředí doporučeno povolit)
                .csrf(csrf -> csrf.disable())

                // Povolení načítání H2 konzole v iframe (vyžaduje stejný původ URL)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))

                // Konfigurace pravidel přístupu k různým URL
                .authorizeHttpRequests(auth -> auth
                        // Povolit přístup k H2 konzoli bez přihlášení
                        .requestMatchers("/h2-console/**").permitAll()

                        // Povolit všechny ostatní požadavky (např. pro vývoj nebo veřejnou část aplikace)
                        .anyRequest().permitAll()
                )

                // Použití výchozí přihlašovací stránky Spring Security
                .formLogin(Customizer.withDefaults());

        // Vytvoření a vrácení instance SecurityFilterChain
        return http.build();
    }
}
