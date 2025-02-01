package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Rozhraní UserRepository slouží jako úložiště pro práci s entitou User.
 * Poskytuje metody pro vyhledávání a validaci uživatelů.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Vyhledá uživatele podle uživatelského jména.
     *
     * @param username uživatelské jméno
     * @return Optional obsahující nalezeného uživatele nebo prázdné, pokud nebyl nalezen
     */
    Optional<User> findByUsername(String username);

    /**
     * Vyhledá uživatele podle e-mailové adresy.
     *
     * @param email e-mailová adresa
     * @return Optional obsahující nalezeného uživatele nebo prázdné, pokud nebyl nalezen
     */
    Optional<User> findByEmail(String email);

    /**
     * Ověří, zda už existuje uživatel s daným uživatelským jménem.
     *
     * @param username uživatelské jméno
     * @return true, pokud uživatel existuje, jinak false
     */
    boolean existsByUsername(String username);

    /**
     * Ověří, zda už existuje uživatel s daným e-mailem.
     *
     * @param email e-mailová adresa
     * @return true, pokud uživatel existuje, jinak false
     */
    boolean existsByEmail(String email);
}