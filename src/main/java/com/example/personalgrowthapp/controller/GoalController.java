package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.Goal;
import com.example.personalgrowthapp.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Třída GoalController slouží jako REST kontroler
 * pro správu cílů (Goals). Obsahuje CRUD operace
 * jako vytvoření, čtení, aktualizace a mazání cílů.
 */
@RestController // Označuje třídu jako REST kontroler (vrací data ve formátu JSON)
@RequestMapping("/api/goals") // Mapuje všechny endpointy tohoto kontroleru na "/api/goals"
public class GoalController {

    @Autowired // Automatické injektování repository třídy pro práci s databází
    private GoalRepository goalRepository;

    /**
     * Metoda pro získání všech cílů z databáze.
     *
     * @return seznam všech cílů (List<Goal>)
     */
    @GetMapping // HTTP GET endpoint na "/api/goals"
    public List<Goal> getAllGoals() {
        return goalRepository.findAll(); // Vrátí všechny cíle uložené v databázi
    }

    /**
     * Metoda pro vytvoření nového cíle a jeho uložení do databáze.
     *
     * @param goal Nový cíl zaslaný v těle požadavku (JSON)
     * @return uložený cíl (včetně automaticky generovaného ID)
     */
    @PostMapping // HTTP POST endpoint na "/api/goals"
    public Goal createGoal(@RequestBody Goal goal) {
        return goalRepository.save(goal); // Uloží cíl do databáze
    }

    /**
     * Metoda pro získání jednoho cíle podle jeho ID.
     *
     * @param id ID cíle z URL cesty
     * @return nalezený cíl nebo null, pokud neexistuje
     */
    @GetMapping("/{id}") // HTTP GET endpoint na "/api/goals/{id}"
    public Goal getGoalById(@PathVariable Long id) {
        return goalRepository.findById(id).orElse(null); // Vyhledá cíl podle ID, pokud neexistuje, vrátí null
    }

    /**
     * Metoda pro aktualizaci existujícího cíle podle jeho ID.
     *
     * @param id ID cíle, který má být aktualizován
     * @param updatedGoal Aktualizované údaje cíle (JSON)
     * @return aktualizovaný cíl nebo null, pokud nebyl nalezen
     */
    @PutMapping("/{id}") // HTTP PUT endpoint na "/api/goals/{id}"
    public Goal updateGoal(@PathVariable Long id, @RequestBody Goal updatedGoal) {
        // Najde cíl podle ID a aktualizuje jeho hodnoty
        return goalRepository.findById(id).map(goal -> {
            goal.setTitle(updatedGoal.getTitle());
            goal.setDescription(updatedGoal.getDescription());
            goal.setStatus(updatedGoal.getStatus());
            goal.setStartDate(updatedGoal.getStartDate());
            goal.setEndDate(updatedGoal.getEndDate());
            return goalRepository.save(goal); // Uloží změny do databáze
        }).orElse(null); // Pokud cíl neexistuje, vrátí null
    }

    /**
     * Metoda pro smazání cíle podle jeho ID.
     *
     * @param id ID cíle, který má být smazán
     */
    @DeleteMapping("/{id}") // HTTP DELETE endpoint na "/api/goals/{id}"
    public void deleteGoal(@PathVariable Long id) {
        goalRepository.deleteById(id); // Smaže cíl podle jeho ID
    }

}
