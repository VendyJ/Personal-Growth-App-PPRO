package com.example.personalgrowthapp.repository;

import com.example.personalgrowthapp.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Označuje, že toto rozhraní je repository (přístup k databázi)
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    // Toto rozhraní poskytuje základní CRUD operace pro entitu Reminder
    // Další dotazy specifické pro Reminder lze přidat zde
}