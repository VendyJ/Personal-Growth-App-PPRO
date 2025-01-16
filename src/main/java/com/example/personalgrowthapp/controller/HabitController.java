
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
}
