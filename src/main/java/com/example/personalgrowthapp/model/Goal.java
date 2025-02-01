package com.example.personalgrowthapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída Goal reprezentuje jeden cíl uživatele v aplikaci.
 * Obsahuje informace o názvu, popisu, stavu, datu začátku a konce,
 * vztahu k uživateli, který cíl vytvořil, a další propojené entity.
 */
@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Column(length = 1000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING) // Použití výčtu pro omezení možných hodnot
    private GoalStatus status;

    @NotNull
    @PastOrPresent
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private LocalDate endDate;

    // ✅ Vztah M:1 mezi cílem a uživatelem (každý cíl patří jednomu uživateli, ale uživatel může mít více cílů)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference //zabrání cyklické serializaci
    private User user;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reminder> reminders = new ArrayList<>();

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoalTask> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgressReport> progressReports = new ArrayList<>();

    public Goal() {}

    public Goal(String name, String description, GoalStatus status, LocalDate startDate, LocalDate endDate, User user) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    // ✅ GETTERY A SETTERY

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GoalStatus getStatus() {
        return status;
    }

    public void setStatus(GoalStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    /**
     * Přidá uživatele k cíli.
     */
    public void assignUser(User user) {
        this.user = user;
        user.getGoals().add(this);
    }

    /**
     * Odebere uživatele z cíle.
     */
    public void removeUser() {
        if (this.user != null) {
            this.user.getGoals().remove(this);
            this.user = null;
        }
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public List<GoalTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<GoalTask> tasks) {
        this.tasks = tasks;
    }

    public List<ProgressReport> getProgressReports() {
        return progressReports;
    }

    public void setProgressReports(List<ProgressReport> progressReports) {
        this.progressReports = progressReports;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + (user != null ? user.getUsername() : "No User Assigned") +
                '}';
    }

    // Výčet pro možné stavy cíle
    public enum GoalStatus {
        IN_PROGRESS,
        COMPLETED,
        ON_HOLD,
        CANCELLED
    }
}