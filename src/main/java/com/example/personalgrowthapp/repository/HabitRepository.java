package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    // Toto rozhraní poskytuje základní CRUD operace pro entitu Habit
    // Další dotazy specifické pro Habit lze přidat zde
}