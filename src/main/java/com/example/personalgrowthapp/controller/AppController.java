package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.Goal;
import com.example.personalgrowthapp.repository.GoalRepository;
import com.example.personalgrowthapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired // Automatické injektování repository třídy pro práci s databází
    private GoalRepository goalRepository;
    @Autowired // Automatické injektování repository třídy pro práci s databází
    private UserRepository userRepository;

    @GetMapping("/goals")
    public String goalsPage(Model model) {
        Goal goal1 = new Goal("Nový task 1", null, null, null, null, null);
        Goal goal2 = new Goal("Nový task 2", null, null, null, null, null);
        List<Goal> goals = List.of(goal1, goal2);//goalRepository.findAll(); // Vrátí všechny cíle uložené v databázi
        model.addAttribute("goals", goals);
        return "goals-list";
    }
}
