package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rozhraní HabitRepository slouží jako úložiště pro práci s entitou Habit.
 * Rozšiřuje JpaRepository, což poskytuje základní metody pro CRUD operace.
 * Díky anotaci @Repository může být toto rozhraní automaticky detekováno a spravováno Springem.
 */
@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    // Zde můžeme přidat vlastní dotazy pomocí Spring Data JPA query methods.
    // Například:
    // List<Habit> findByName(String name);
    // List<Habit> findByFrequency(String frequency);
}