package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.Goal;
import com.example.personalgrowthapp.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    @Autowired
    private GoalRepository goalRepository;

    //Metoda pro získání všech cílů
    @GetMapping
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    //Metoda pro vytvoření nového cíle
    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalRepository.save(goal);
    }

    //Metoda pro získání cíle podle ID
    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        return goalRepository.findById(id).orElse(null);
    }

    //Metoda pro aktualizaci cíle podle ID
    @PutMapping("/{id}")
    public Goal updateGoal(@PathVariable Long id, @RequestBody Goal updatedGoal) {
        return goalRepository.findById(id).map(goal -> {
            goal.setTitle(updatedGoal.getTitle());
            goal.setDescription(updatedGoal.getDescription());
            goal.setStatus(updatedGoal.getStatus());
            goal.setStartDate(updatedGoal.getStartDate());
            goal.setEndDate(updatedGoal.getEndDate());
            return goalRepository.save(goal);
        }).orElse(null);
    }

    //Metoda pro smazání cíle podle ID
    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalRepository.deleteById(id);
    }

}
