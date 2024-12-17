package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    // Toto rozhraní poskytuje základní CRUD operace pro entitu Reminder
    // Další dotazy specifické pro Reminder lze přidat zde
}