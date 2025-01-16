package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Třída Habit reprezentuje zvyk uživatele v aplikaci.
 * Zahrnuje informace o názvu zvyku, jeho frekvenci, stavu splnění
 * a vztahu k uživateli, který zvyk vlastní.
 */
@Entity // Označuje tuto třídu jako entitu, která se mapuje na tabulku v databázi.
public class Habit {

    @Id // Označuje primární klíč tabulky.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primární klíč se bude generovat automaticky databází.
    private Long id;

    @NotNull // Název zvyku nesmí být null (prázdný).
    @Size(max = 255) // Maximální délka názvu je 255 znaků.
    private String name; // Název zvyku (např. "běhání", "meditace").

    @NotNull // Frekvence nesmí být null.
    @Size(max = 50) // Maximální délka řetězce pro frekvenci je 50 znaků.
    private String frequency; // Frekvence sledování zvyku (např. "denně", "týdně").

    @NotNull // Tento atribut nesmí být null.
    private boolean isCompleted; // Indikátor, zda byl zvyk splněn (true) nebo ne (false).

    @ManyToOne // Vztah "mnoho zvyků patří jednomu uživateli".
    @JoinColumn(name = "user_id") // Název sloupce, který bude cizím klíčem k tabulce uživatelů.
    private User user;

    // Konstruktor bez parametrů je povinný pro JPA.
    public Habit() {}

    /**
     * Konstruktor s parametry pro vytvoření zvyku s výchozími hodnotami.
     *
     * @param name       Název zvyku.
     * @param frequency  Frekvence sledování zvyku.
     * @param isCompleted Stav zvyku, zda je splněný.
     * @param user       Uživatel, kterému zvyk patří.
     */
    public Habit(String name, String frequency, boolean isCompleted, User user) {
        this.name = name;
        this.frequency = frequency;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    // Gettery a settery pro přístup a nastavení hodnot atributů.

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

    /**
     * Metoda toString vrací textovou reprezentaci objektu Habit.
     * Používá se např. pro ladění.
     *
     * @return Textová reprezentace zvyku.
     */
    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", frequency='" + frequency + '\'' +
                ", isCompleted=" + isCompleted +
                ", user=" + (user != null ? user.getUsername() : "No User Assigned") +
                '}';
    }
}
