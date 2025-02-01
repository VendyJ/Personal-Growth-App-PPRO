package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.Goal;
import com.example.personalgrowthapp.service.GoalService;
import com.example.personalgrowthapp.repository.GoalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler pro správu cílů.
 */
@Controller
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    /**
     * Zobrazí seznam všech cílů.
     */
    @GetMapping
    public String listGoals(Model model) {
        model.addAttribute("goals", goalService.getAllGoals()); // ✅ Použití GoalService místo přímého repository
        model.addAttribute("goal", new Goal());
        return "goals-list";
    }

    /**
     * Přidání nového cíle.
     */
    @PostMapping("/add")
    public String addGoal(@RequestParam String name) {
        goalService.createGoal(name); // ✅ Použití GoalService pro vytvoření cíle
        System.out.println("Přidán nový cíl: " + name);
        return "redirect:/goals";
    }

    /**
     * Zobrazení formuláře pro editaci cíle.
     */
    @GetMapping("/edit/{id}")
    public String editGoal(@PathVariable Long id, Model model) {
        Goal goal = goalService.findById(id).orElse(null);
        if (goal == null) {
            return "redirect:/goals"; // Pokud cíl neexistuje, přesměruj na seznam
        }
        model.addAttribute("goal", goal);
        return "edit-goal";
    }

    /**
     * Aktualizace existujícího cíle.
     */
    @PostMapping("/edit/{id}")
    public String updateGoal(@PathVariable Long id, @RequestParam String name) {
        goalService.updateGoal(id, name); // ✅ Použití GoalService místo repository
        return "redirect:/goals";
    }

    /**
     * Odstranění cíle.
     */
    @GetMapping("/delete/{id}")
    public String deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id); // ✅ Použití GoalService pro odstranění cíle
        return "redirect:/goals";
    }

    /**
     * API pro získání všech cílů (pro frontend).
     */
    @GetMapping("/api")
    public ResponseEntity<List<Goal>> getAllGoals() {
        return ResponseEntity.ok(goalService.getAllGoals());
    }
}