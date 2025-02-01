package com.example.personalgrowthapp.service;

import com.example.personalgrowthapp.model.User;
import com.example.personalgrowthapp.model.Goal;
import com.example.personalgrowthapp.model.Role;
import com.example.personalgrowthapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Služba pro správu uživatelů.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(String username, String password, String email, Role role) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Uživatel s tímto jménem již existuje.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Uživatel s tímto e-mailem již existuje.");
        }

        User newUser = new User(username, passwordEncoder.encode(password), email, role);
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("Uživatel nenalezen."));
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    /**
     * ✅ Přidání cíle uživateli.
     */
    @Transactional
    public void assignGoalToUser(Long userId, Goal goal) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Uživatel nenalezen."));

        user.getGoals().add(goal);
        goal.setUser(user); // Nastavení uživatele k cíli

        userRepository.save(user);
    }

    /**
     * ✅ Odebrání cíle od uživatele.
     */
    @Transactional
    public void removeGoalFromUser(Long userId, Long goalId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Uživatel nenalezen."));

        user.getGoals().removeIf(goal -> goal.getId().equals(goalId));
        userRepository.save(user);
    }
}