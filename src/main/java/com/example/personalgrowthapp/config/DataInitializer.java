// src/main/java/com/example/personalgrowthapp/config/DataInitializer.java
package com.example.personalgrowthapp.config;

import com.example.personalgrowthapp.model.User;
import com.example.personalgrowthapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {

    //udělat konstruktor a dělat injekce v něm
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Mock uživatelé - pozor na dvojité hashování hesel uživatelů - nedělat na dvou místech!
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("password1"));
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("password2"));
        user2.setEmail("user2@example.com");

        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword(passwordEncoder.encode("password3"));
        user3.setEmail("user3@example.com");

        // Uložení do databáze
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}