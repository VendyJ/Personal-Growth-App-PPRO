package com.example.personalgrowthapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity // Označuje třídu jako entitu pro JPA (bude se mapovat do tabulky v databázi)
public class User {

    @Id // Primární klíč entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Automaticky generovaná hodnota (např. IDENTITY znamená, že databáze vytvoří hodnotu)
    private Long id;

    private String username; // Uživatelské jméno
    private String password; // Heslo (před nasazením do produkce zašifrujeme)
    private String email; // Email uživatele

    @OneToMany(mappedBy = "user") // Vztah k připomenutím vytvořeným uživatelem (jeden uživatel může mít více připomenutí)
    private List<Reminder> reminders;

    @OneToMany(mappedBy = "user") // Vztah k zvykům uživatele (jeden uživatel může mít více zvyků)
    private List<Habit> habits;

    // Gettery a settery - metody pro přístup k privátním atributům třídy
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }
}
