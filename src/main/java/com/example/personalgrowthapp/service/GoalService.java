package com.example.personalgrowthapp.service;

import com.example.personalgrowthapp.model.Goal;
import com.example.personalgrowthapp.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Služba pro správu cílů uživatelů.
 */
@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    /**
     * Získá všechny cíle v systému.
     */
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    /**
     * Najde cíl podle ID.
     */
    public Optional<Goal> findById(Long id) {
        return goalRepository.findById(id);
    }

    /**
     * Vytvoří nový cíl.
     */
    public Goal createGoal(String name) {
        Goal goal = new Goal();
        goal.setName(name);
        return goalRepository.save(goal);
    }

    /**
     * Aktualizuje existující cíl.
     */
    public Goal updateGoal(Long id, String name) {
        return goalRepository.findById(id).map(goal -> {
            goal.setName(name);
            return goalRepository.save(goal);
        }).orElseThrow(() -> new IllegalArgumentException("Cíl s ID " + id + " nebyl nalezen."));
    }

    /**
     * Odstraní cíl podle ID.
     */
    public void deleteGoal(Long id) {
        if (goalRepository.existsById(id)) {
            goalRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cíl s ID " + id + " nebyl nalezen.");
        }
    }
}