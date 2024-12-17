package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    // Zde můžeme později přidat vlastní dotazy, pokud budeme potřebovat
}