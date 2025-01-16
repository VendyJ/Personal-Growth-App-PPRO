package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rozhraní ReminderRepository slouží jako úložiště pro práci s entitou Reminder.
 * Rozšiřuje JpaRepository, což poskytuje základní metody pro CRUD operace.
 * Díky anotaci @Repository může být toto rozhraní automaticky detekováno a spravováno Springem.
 */
@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    // Zde můžeme přidat vlastní dotazy pomocí Spring Data JPA query methods.
    // Například:
    // List<Reminder> findByUserId(Long userId);
    // List<Reminder> findByGoalId(Long goalId);
    // List<Reminder> findByReminderTimeBefore(Date date);
}