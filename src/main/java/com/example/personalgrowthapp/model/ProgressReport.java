package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Třída ProgressReport reprezentuje zprávu o pokroku v rámci cíle.
 */
@Entity
public class ProgressReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @Size(max = 1000)
    private String description;

    @NotNull
    @Min(0) // Procenta pokroku musí být mezi 0 a 100
    @Max(100)
    private int progressPercentage;

    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    public ProgressReport() {}

    public ProgressReport(LocalDate date, String description, int progressPercentage, Goal goal) {
        this.date = date;
        this.description = description;
        this.progressPercentage = progressPercentage;
        this.goal = goal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "ProgressReport{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", progressPercentage=" + progressPercentage +
                ", goal=" + (goal != null ? goal.getName() : "No Goal Assigned") +
                '}';
    }
}