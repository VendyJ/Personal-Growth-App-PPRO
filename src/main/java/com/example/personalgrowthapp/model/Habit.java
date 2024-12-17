package com.example.personalgrowthapp.model;

import jakarta.persistence.*;

@Entity // Označuje třídu jako entitu pro JPA
public class Habit {

    @Id // Primární klíč entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automaticky generovaná hodnota
    private Long id;

    private String name; // Název zvyku (např. "běhání" nebo "meditace")
    private String frequency; // Frekvence sledování (např. "denně" nebo "týdně")
    private boolean isCompleted; // Stav, zda byl zvyk splněn, nebo ne

    @ManyToOne // Vztah k uživateli, který tento zvyk vlastní
    private User user;

    // Gettery a settery - metody pro přístup k privátním atributům třídy

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
