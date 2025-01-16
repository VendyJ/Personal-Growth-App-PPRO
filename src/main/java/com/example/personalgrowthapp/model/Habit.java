package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Třída Habit reprezentuje zvyk uživatele v aplikaci.
 * Zahrnuje informace o názvu zvyku, jeho frekvenci, stavu splnění
 * a vztahu k uživateli, který zvyk vlastní.
 */
@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name; // Název zvyku (např. "běhání", "meditace").

    @NotNull
    @Enumerated(EnumType.STRING) // Použití výčtu pro frekvenci.
    private Frequency frequency; // Frekvence sledování zvyku.

    @NotNull
    private boolean isCompleted; // Indikátor, zda byl zvyk splněn.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Habit() {}

    public Habit(String name, Frequency frequency, boolean isCompleted, User user) {
        this.name = name;
        this.frequency = frequency;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    // Gettery a settery

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

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
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

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", frequency=" + frequency +
                ", isCompleted=" + isCompleted +
                ", user=" + (user != null ? user.getUsername() : "No User Assigned") +
                '}';
    }

    /**
     * Výčet pro frekvence sledování zvyku.
     */
    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
}
