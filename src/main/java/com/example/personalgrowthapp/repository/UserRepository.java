package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Rozhraní UserRepository slouží jako úložiště pro práci s entitou User.
 * Rozšiřuje JpaRepository, což poskytuje základní metody pro CRUD operace.
 * Anotace @Repository označuje tuto třídu jako Spring komponentu pro přístup k databázi.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Vyhledá uživatele podle uživatelského jména.
     * Spring Data JPA automaticky vygeneruje SQL dotaz na základě názvu metody.
     *
     * @param username uživatelské jméno
     * @return Optional obsahující nalezeného uživatele, nebo prázdné, pokud nebyl nalezen
     */
    Optional<User> findByUsername(String username);

    /**
     * Vyhledá uživatele podle e-mailové adresy.
     * Tuto metodu lze použít například pro validaci při registraci.
     *
     * @param email e-mailová adresa
     * @return Optional obsahující nalezeného uživatele, nebo prázdné, pokud nebyl nalezen
     */
    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // Další metody specifické pro entitu User lze přidat zde.
}