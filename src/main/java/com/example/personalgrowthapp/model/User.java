package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity // Označuje třídu jako entitu pro JPA (bude se mapovat do tabulky v databázi)
@Table(name = "app_user") // Změna názvu tabulky, aby nekolidovala s rezervovanými klíčovými slovy
public class User {

    @Id // Primární klíč entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automaticky generovaná hodnota (např. IDENTITY znamená, že databáze vytvoří hodnotu)
    private Long id;

    @Column(nullable = false, unique = true) // Uživatelské jméno musí být unikátní a nemůže být null
    private String username;

    @Column(nullable = false) // Heslo je povinné
    private String password;

    @Column(nullable = false, unique = true) // Email musí být unikátní a nemůže být null
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Vztah k připomenutím (1:N), kaskádové mazání
    private List<Reminder> reminders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Vztah k zvykům (1:N), kaskádové mazání
    private List<Habit> habits;

    // Konstruktor bez parametrů (vyžadován JPA)
    public User() {
    }

    // Konstruktor pro snadnější vytváření instancí
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Gettery a settery
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}