package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.Reminder;
import com.example.personalgrowthapp.repository.ReminderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Označuje, že tato třída bude REST API kontroler
@RequestMapping("/api/reminders") // Základní URL pro všechny metody tohoto kontroleru
public class ReminderController {

    @Autowired // Automatická injekce instance ReminderRepository
    private ReminderRepository reminderRepository;

    // Získání všech připomínek
    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll(); // Vrátí seznam všech připomínek
    }

    // Vytvoření nové připomínky
    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderRepository.save(reminder); // Uloží novou připomínku do databáze
    }

    // Získání připomínky podle id
    @GetMapping("/{id}")
    public Reminder getReminderById(@PathVariable Long id) {
        return reminderRepository.findById(id).orElse(null); // Najde připomínku, nebo vrátí null
    }

    // Aktualizace připomínky podle id
    @PutMapping("/{id}")
    public Reminder updateReminder(@PathVariable Long id, @RequestBody Reminder updatedReminder) {
        return reminderRepository.findById(id).map(reminder -> {
            reminder.setMessage(updatedReminder.getMessage());
            reminder.setReminderTime(updatedReminder.getReminderTime());
            reminder.setGoal(updatedReminder.getGoal());
            reminder.setUser(updatedReminder.getUser());
            return reminderRepository.save(reminder); // Aktualizuje připomínku
        }).orElse(null);
    }

    // Smazání připomínky podle id
    @DeleteMapping("/{id}")
    public void deleteReminder(@PathVariable Long id) {
        reminderRepository.deleteById(id); // Smaže připomínku podle id
    }
}
