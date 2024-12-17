package com.example.personalgrowthapp.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity // Označuje třídu jako entitu pro JPA (bude se mapovat do tabulky v databázi)
public class Reminder {

    @Id // Primární klíč entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automaticky generovaná hodnota (např. IDENTITY znamená, že databáze vytvoří hodnotu)
    private Long id;

    private String message; //Zpráva připomenutí
    private Date remindDate; //Datum, kdy má být připomenutí vyvoláno

    @ManyToOne // Vztah k uživateli, který toto připomenutí vlastní (mnoho připomenutí může patřit jednomu uživateli)
    private User user;

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

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
