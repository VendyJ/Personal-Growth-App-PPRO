
package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.Habit;
import com.example.personalgrowthapp.repository.HabitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Třída HabitController slouží jako REST API kontroler
 * pro správu zvyků (Habit). Obsahuje CRUD operace
 * jako vytvoření, čtení, aktualizace a mazání zvyků.
 */
@RestController // Označuje třídu jako REST kontroler (vrací data ve formátu JSON)
@RequestMapping("/api/habits") // Mapuje všechny endpointy tohoto kontroleru na "/api/habits"
public class HabitController {

    @Autowired // Automatická injekce instance třídy HabitRepository
    private HabitRepository habitRepository;

    /**
     * Metoda pro získání všech zvyků z databáze.
     *
     * @return seznam všech zvyků (List<Habit>)
     */
    @GetMapping // HTTP GET endpoint na "/api/habits"
    public List<Habit> getAllHabits() {
        return habitRepository.findAll(); // Vrátí všechny zvyky uložené v databázi
    }

    /**
     * Metoda pro vytvoření nového zvyku a jeho uložení do databáze.
     *
     * @param habit Nový zvyk zaslaný v těle požadavku (JSON)
     * @return uložený zvyk (včetně automaticky generovaného ID)
     */
    @PostMapping // HTTP POST endpoint na "/api/habits"
    public Habit createHabit(@RequestBody Habit habit) {
        return habitRepository.save(habit); // Uloží nový zvyk do databáze
    }

    /**
     * Metoda pro získání konkrétního zvyku podle jeho ID.
     *
     * @param id ID zvyku z URL cesty
     * @return nalezený zvyk nebo null, pokud neexistuje
     */
    @GetMapping("/{id}") // HTTP GET endpoint na "/api/habits/{id}"
    public Habit getHabitById(@PathVariable Long id) {
        return habitRepository.findById(id).orElse(null); // Najde zvyk podle ID, pokud neexistuje, vrátí null
    }

    /**
     * Metoda pro aktualizaci existujícího zvyku podle jeho ID.
     *
     * @param id ID zvyku, který má být aktualizován
     * @param updatedHabit Aktualizované údaje zvyku (JSON)
     * @return aktualizovaný zvyk nebo null, pokud nebyl nalezen
     */
    @PutMapping("/{id}") // HTTP PUT endpoint na "/api/habits/{id}"
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit updatedHabit) {
        // Najde zvyk podle ID a aktualizuje jeho hodnoty
        return habitRepository.findById(id).map(habit -> {
            habit.setName(updatedHabit.getName()); // Aktualizuje název
            habit.setFrequency(updatedHabit.getFrequency()); // Aktualizuje frekvenci
            habit.setCompleted(updatedHabit.isCompleted()); // Aktualizuje stav dokončení
            habit.setUser(updatedHabit.getUser()); // Aktualizuje uživatele
            return habitRepository.save(habit); // Uloží změny do databáze
        }).orElse(null); // Pokud zvyk neexistuje, vrátí null
    }

    /**
     * Metoda pro smazání zvyku podle jeho ID.
     *
     * @param id ID zvyku, který má být smazán
     */
    @DeleteMapping("/{id}") // HTTP DELETE endpoint na "/api/habits/{id}"
    public void deleteHabit(@PathVariable Long id) {
        habitRepository.deleteById(id); // Smaže zvyk podle ID
    }
}