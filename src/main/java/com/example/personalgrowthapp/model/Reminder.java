package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Třída Reminder reprezentuje připomenutí v aplikaci.
 * Obsahuje informace o zprávě připomenutí, datu a času připomenutí
 * a vztahy k uživateli a cíli, ke kterým připomenutí patří.
 */
@Entity // Označuje třídu jako entitu, která se mapuje na tabulku v databázi
public class Reminder {

    @Id // Primární klíč tabulky
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primární klíč se bude generovat automaticky databází
    private Long id;

    @NotNull // Zpráva nesmí být null
    @Size(max = 255) // Maximální délka zprávy je 255 znaků
    private String message;

    @NotNull // Datum a čas připomenutí nesmí být null
    @Temporal(TemporalType.TIMESTAMP) // Mapování na SQL typ TIMESTAMP
    private Date reminderTime;

    @NotNull // Každé připomenutí musí být přiřazeno k uživateli
    @ManyToOne // Vztah: více připomenutí může patřit jednomu uživateli
    @JoinColumn(name = "user_id", nullable = false) // Název sloupce v databázi
    private User user;

    @NotNull // Každé připomenutí musí být přiřazeno k cíli
    @ManyToOne // Vztah: více připomenutí může být spojeno s jedním cílem
    @JoinColumn(name = "goal_id", nullable = false) // Název sloupce v databázi
    private Goal goal;

    // Konstruktor bez parametrů
    public Reminder() {}

    /**
     * Konstruktor s parametry.
     *
     * @param message zpráva připomenutí
     * @param reminderTime datum a čas připomenutí
     * @param user uživatel, který vlastní připomenutí
     * @param goal cíl, ke kterému připomenutí patří
     */
    public Reminder(String message, Date reminderTime, User user, Goal goal) {
        this.message = message;
        this.reminderTime = reminderTime;
        this.user = user;
        this.goal = goal;
    }

    // Gettery a settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Date reminderTime) {
        this.reminderTime = reminderTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", reminderTime=" + reminderTime +
                ", user=" + (user != null ? user.getUsername() : "null") +
                ", goal=" + (goal != null ? goal.getTitle() : "null") +
                '}';
    }
}