package com.example.personalgrowthapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Třída Goal reprezentuje jeden cíl uživatele v aplikaci.
 * Obsahuje informace o názvu, popisu, stavu, datu začátku a konce,
 * vztahu k uživateli, který cíl vytvořil, a připomenutích spojených s cílem.
 */
@Entity // Označuje tuto třídu jako entitu (bude se mapovat do tabulky v databázi).
public class Goal {

    @Id // Označuje primární klíč tabulky.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primární klíč se bude generovat automaticky databází.
    private Long id;

    @NotNull // Tento atribut nesmí být null (prázdný).
    @Size(max = 255) // Maximální délka názvu je 255 znaků.
    private String title;

    @Column(length = 1000) // Popis cíle má maximální délku 1000 znaků.
    private String description;

    @NotNull // Stav cíle musí být vždy vyplněný (např. "rozpracovaný", "dokončený").
    private String status;

    @Temporal(TemporalType.DATE) // Tento atribut je typu Date a ukládá pouze datum (bez času).
    @PastOrPresent // Datum začátku cíle musí být v minulosti nebo dnešní datum.
    private Date startDate;

    @Temporal(TemporalType.DATE) // Tento atribut je typu Date a ukládá pouze datum (bez času).
    @FutureOrPresent // Datum konce cíle musí být v budoucnosti nebo dnešní datum.
    private Date endDate;

    @ManyToOne // Vztah "mnoho cílů patří jednomu uživateli".
    @JoinColumn(name = "user_id") // Název sloupce, který se bude používat jako cizí klíč k tabulce uživatelů.
    private User user;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    // Vztah "jeden cíl má mnoho připomenutí".
    // MappedBy říká, že připomenutí odkazují na tento cíl (přes atribut "goal" v entitě Reminder).
    // CascadeType.ALL zajistí, že operace s cílem (např. smazání) se projeví i na připomenutích.
    // OrphanRemoval zajistí automatické smazání připomenutí, které byly odebrány ze seznamu.
    private List<Reminder> reminders = new ArrayList<>(); // Inicializace seznamu připomenutí (aby nebyl null).

    // Konstruktor bez parametrů je potřeba pro JPA (Java Persistence API).
    public Goal() {}

    /**
     * Konstruktor s parametry pro vytvoření cíle s výchozími hodnotami.
     *
     * @param title       Název cíle.
     * @param description Popis cíle.
     * @param status      Stav cíle (např. "rozpracovaný").
     * @param startDate   Datum začátku cíle.
     * @param endDate     Datum konce cíle.
     * @param user        Uživatel, kterému cíl patří.
     */
    public Goal(String title, String description, String status, Date startDate, Date endDate, User user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    // Gettery a settery umožňují přístup a nastavení hodnot atributů.

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    /**
     * Metoda toString vrací textovou reprezentaci objektu Goal.
     * Používá se např. pro ladění.
     *
     * @return Textová reprezentace cíle.
     */
    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + (user != null ? user.getUsername() : "No User Assigned") +
                '}';
    }
}
