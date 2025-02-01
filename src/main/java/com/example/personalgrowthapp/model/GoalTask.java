package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Třída GoalTask reprezentuje úkol spojený s cílem uživatele.
 */
@Entity
public class GoalTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull
    @FutureOrPresent // Termín musí být v budoucnosti nebo současnosti
    private LocalDate deadline;

    @NotNull
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    public GoalTask() {}

    public GoalTask(String title, String description, LocalDate deadline, boolean isCompleted, Goal goal) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.goal = goal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "GoalTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", isCompleted=" + isCompleted +
                ", goal=" + (goal != null ? goal.getName() : "No Goal Assigned") +
                '}';
    }
}