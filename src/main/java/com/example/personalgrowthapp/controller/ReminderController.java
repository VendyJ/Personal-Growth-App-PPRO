package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.Reminder;
import com.example.personalgrowthapp.repository.ReminderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Třída ReminderController slouží jako REST API kontroler
 * pro správu připomínek (Reminder). Obsahuje CRUD operace
 * jako vytvoření, čtení, aktualizace a mazání připomínek.
 */
@RestController // Označuje třídu jako REST kontroler (vrací data ve formátu JSON)
@RequestMapping("/api/reminders") // Mapuje všechny endpointy tohoto kontroleru na "/api/reminders"
public class ReminderController {

    @Autowired // Automatická injekce instance třídy ReminderRepository
    private ReminderRepository reminderRepository;

    /**
     * Metoda pro získání všech připomínek z databáze.
     *
     * @return seznam všech připomínek (List<Reminder>)
     */
    @GetMapping // HTTP GET endpoint na "/api/reminders"
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll(); // Vrátí všechny připomínky uložené v databázi
    }

    /**
     * Metoda pro vytvoření nové připomínky a její uložení do databáze.
     *
     * @param reminder Nová připomínka zaslaná v těle požadavku (JSON)
     * @return uložená připomínka (včetně automaticky generovaného ID)
     */
    @PostMapping // HTTP POST endpoint na "/api/reminders"
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderRepository.save(reminder); // Uloží novou připomínku do databáze
    }

    /**
     * Metoda pro získání konkrétní připomínky podle jejího ID.
     *
     * @param id ID připomínky z URL cesty
     * @return nalezená připomínka nebo null, pokud neexistuje
     */
    @GetMapping("/{id}") // HTTP GET endpoint na "/api/reminders/{id}"
    public Reminder getReminderById(@PathVariable Long id) {
        return reminderRepository.findById(id).orElse(null); // Najde připomínku podle ID, pokud neexistuje, vrátí null
    }

    /**
     * Metoda pro aktualizaci existující připomínky podle jejího ID.
     *
     * @param id ID připomínky, která má být aktualizována
     * @param updatedReminder Aktualizované údaje připomínky (JSON)
     * @return aktualizovaná připomínka nebo null, pokud nebyla nalezena
     */
    @PutMapping("/{id}") // HTTP PUT endpoint na "/api/reminders/{id}"
    public Reminder updateReminder(@PathVariable Long id, @RequestBody Reminder updatedReminder) {
        // Najde připomínku podle ID a aktualizuje její hodnoty
        return reminderRepository.findById(id).map(reminder -> {
            reminder.setMessage(updatedReminder.getMessage()); // Aktualizuje zprávu
            reminder.setReminderTime(updatedReminder.getReminderTime()); // Aktualizuje čas připomínky
            reminder.setGoal(updatedReminder.getGoal()); // Aktualizuje spojený cíl
            reminder.setUser(updatedReminder.getUser()); // Aktualizuje uživatele spojeného s připomínkou
            return reminderRepository.save(reminder); // Uloží změny do databáze
        }).orElse(null); // Pokud připomínka neexistuje, vrátí null
    }

    /**
     * Metoda pro smazání připomínky podle jejího ID.
     *
     * @param id ID připomínky, která má být smazána
     */
    @DeleteMapping("/{id}") // HTTP DELETE endpoint na "/api/reminders/{id}"
    public void deleteReminder(@PathVariable Long id) {
        reminderRepository.deleteById(id); // Smaže připomínku podle ID
    }
}