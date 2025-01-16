package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.User;
import com.example.personalgrowthapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Třída UserController slouží jako REST API kontroler
 * pro správu uživatelů (User). Obsahuje CRUD operace
 * a zajišťuje bezpečnost a validaci dat.
 */
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        // Kontrola duplicity uživatelského jména a emailu
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(null);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }

        // Hashování hesla
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Hashování hesla
            user.setEmail(updatedUser.getEmail());
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}