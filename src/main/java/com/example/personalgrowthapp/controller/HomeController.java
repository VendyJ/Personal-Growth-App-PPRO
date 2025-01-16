package com.example.personalgrowthapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Kontroler pro hlavní stránku aplikace.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Název šablony "index.html"
    }
}