package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Třída User reprezentuje uživatele aplikace.
 * Obsahuje informace o uživatelském jméně, hesle, emailu
 * a vztahy k připomenutím a zvykům.
 */
@Entity // Označuje třídu jako entitu pro JPA (bude se mapovat do tabulky v databázi)
@Table(name = "app_user") // Změna názvu tabulky, aby se předešlo konfliktům s rezervovanými klíčovými slovy
public class User {

    @Id // Primární klíč tabulky
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primární klíč se bude generovat automaticky databází
    private Long id;

    @NotNull // Uživatelské jméno nesmí být null
    @Size(max = 50) // Maximální délka uživatelského jména je 50 znaků
    @Column(nullable = false, unique = true) // Uživatelské jméno musí být unikátní a nemůže být null
    private String username;

    @NotNull // Heslo nesmí být null
    @Size(min = 8, max = 255) // Minimální délka hesla je 8 znaků, maximální 255
    @Column(nullable = false) // Heslo je povinné
    private String password;

    @NotNull // Email nesmí být null
    @Email // Validace emailového formátu
    @Size(max = 100) // Maximální délka emailu je 100 znaků
    @Column(nullable = false, unique = true) // Email musí být unikátní a nemůže být null
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Vztah k připomenutím (1:N), kaskádové mazání
    private List<Reminder> reminders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Vztah k zvykům (1:N), kaskádové mazání
    private List<Habit> habits;

    /**
     * Konstruktor bez parametrů (vyžadován JPA).
     */
    public User() {}

    /**
     * Konstruktor s parametry pro snadnější vytváření instancí.
     *
     * @param username uživatelské jméno
     * @param password heslo
     * @param email emailová adresa
     */
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

    /**
     * Přehledný výpis objektu User ve formě řetězce.
     *
     * @return textová reprezentace uživatele
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}