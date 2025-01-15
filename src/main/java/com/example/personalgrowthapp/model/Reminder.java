package com.example.personalgrowthapp.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity // Označuje třídu jako entitu pro JPA (bude se mapovat do tabulky v databázi)
public class Reminder {

    @Id // Primární klíč entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automaticky generovaná hodnota (např. IDENTITY znamená, že databáze vytvoří hodnotu)
    private Long id;

    private String message; //Zpráva připomenutí

    @Temporal(TemporalType.TIMESTAMP)
    private Date reminderTime; //Datum a čas, kdy má být připomenutí vyvoláno

    @ManyToOne // Vztah k uživateli, který toto připomenutí vlastní (mnoho připomenutí může patřit jednomu uživateli)
    @JoinColumn(name = "user_id", nullable = false) // Název sloupce v databázi pro id uživatele
    private User user;

    @ManyToOne // Vztah k cíli (opět N:1, každá připomínka může být spojena s jedním cílem
    @JoinColumn(name = "goal_id", nullable = false) // Název sloupce v databázi pro id cíle
    private Goal goal;

    // Gettery a settery - metody pro přístup k privátním atributům třídy
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
}
