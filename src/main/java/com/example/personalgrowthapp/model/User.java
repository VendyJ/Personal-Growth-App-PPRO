package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * Třída User reprezentuje uživatele aplikace.
 * Obsahuje informace o uživatelském jméně, hesle, emailu,
 * a vztahy k cílům, připomenutím a zvykům.
 */
@Entity
@Table(name = "app_user") // Změna názvu tabulky, aby se předešlo konfliktům
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(min = 8, max = 255)
    @Column(nullable = false)
    private String password;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reminder> reminders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits;

    /**
     * Konstruktor bez parametrů (vyžadován JPA).
     */
    public User() {}

    /**
     * Konstruktor s parametry pro snadnější vytváření instancí.
     *
     * @param username uživatelské jméno
     * @param password heslo (je automaticky hashováno při použití setteru)
     * @param email emailová adresa
     */
    public User(String username, String password, String email) {
        this.username = username;
        setPassword(password); // Nastavení hesla přes setter pro hashování
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

    /**
     * Nastaví heslo. Heslo je automaticky hashováno pomocí BCrypt.
     *
     * @param password čisté heslo
     */
    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
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
