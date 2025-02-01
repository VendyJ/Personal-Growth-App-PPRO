package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * Třída Reminder reprezentuje připomenutí v aplikaci.
 * Obsahuje informace o zprávě připomenutí, datu a času připomenutí
 * a vztahy k uživateli a cíli, ke kterým připomenutí patří.
 */
@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String message;

    @NotNull
    @FutureOrPresent // Připomenutí musí být v současnosti nebo budoucnosti
    private LocalDateTime reminderTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    public Reminder() {}

    public Reminder(String message, LocalDateTime reminderTime, User user, Goal goal) {
        this.message = message;
        this.reminderTime = reminderTime;
        this.user = user;
        this.goal = goal;
    }

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

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
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
                ", goal=" + (goal != null ? goal.getName() : "null") +
                '}';
    }
}