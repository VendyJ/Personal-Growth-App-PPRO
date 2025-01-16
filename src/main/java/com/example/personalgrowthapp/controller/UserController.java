package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.User;
import com.example.personalgrowthapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Třída UserController slouží jako REST API kontroler
 * pro správu uživatelů (User). Obsahuje CRUD operace
 * jako vytvoření, čtení, aktualizace a mazání uživatelů.
 */
@RestController // Označuje třídu jako REST kontroler (vrací data ve formátu JSON)
@RequestMapping("/api/users") // Mapuje všechny endpointy tohoto kontroleru na "/api/users"
public class UserController {

    @Autowired // Automatická injekce instance třídy UserRepository
    private UserRepository userRepository;

    /**
     * Metoda pro získání všech uživatelů z databáze.
     *
     * @return seznam všech uživatelů (List<User>)
     */
    @GetMapping // HTTP GET endpoint na "/api/users"
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Vrátí všechny uživatele uložené v databázi
    }

    /**
     * Metoda pro vytvoření nového uživatele a jeho uložení do databáze.
     *
     * @param user Nový uživatel zaslaný v těle požadavku (JSON)
     * @return uložený uživatel (včetně automaticky generovaného ID)
     */
    @PostMapping // HTTP POST endpoint na "/api/users"
    public User createUser(@RequestBody User user) {
        return userRepository.save(user); // Uloží nového uživatele do databáze
    }

    /**
     * Metoda pro získání konkrétního uživatele podle jeho ID.
     *
     * @param id ID uživatele z URL cesty
     * @return nalezený uživatel nebo null, pokud neexistuje
     */
    @GetMapping("/{id}") // HTTP GET endpoint na "/api/users/{id}"
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null); // Najde uživatele podle ID, pokud neexistuje, vrátí null
    }

    /**
     * Metoda pro aktualizaci existujícího uživatele podle jeho ID.
     *
     * @param id ID uživatele, který má být aktualizován
     * @param updatedUser Aktualizované údaje uživatele (JSON)
     * @return aktualizovaný uživatel nebo null, pokud nebyl nalezen
     */
    @PutMapping("/{id}") // HTTP PUT endpoint na "/api/users/{id}"
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        // Najde uživatele podle ID a aktualizuje jeho hodnoty
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername()); // Aktualizuje uživatelské jméno
            user.setPassword(updatedUser.getPassword()); // Aktualizuje heslo
            user.setEmail(updatedUser.getEmail()); // Aktualizuje email
            return userRepository.save(user); // Uloží změny do databáze
        }).orElse(null); // Pokud uživatel neexistuje, vrátí null
    }

    /**
     * Metoda pro smazání uživatele podle jeho ID.
     *
     * @param id ID uživatele, který má být smazán
     */
    @DeleteMapping("/{id}") // HTTP DELETE endpoint na "/api/users/{id}"
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id); // Smaže uživatele podle ID
    }
}