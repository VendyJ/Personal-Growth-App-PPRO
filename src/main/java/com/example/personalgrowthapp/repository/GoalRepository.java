package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rozhraní GoalRepository slouží jako úložiště pro práci s entitou Goal.
 * Rozšiřuje JpaRepository, což poskytuje základní metody pro CRUD operace.
 * @author [Tvoje jméno]
 */
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    // Zde můžeme přidat vlastní dotazy pomocí metodových signatur (Spring Data JPA query methods).
    // Například: List<Goal> findByStatus(String status);
}