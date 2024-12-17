package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.Reminder;
import com.example.personalgrowthapp.repository.ReminderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired
    private ReminderRepository reminderRepository;

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    @GetMapping("/{id}")
    public Reminder getReminderById(@PathVariable Long id) {
        return reminderRepository.findById(id).orElse(null);
    }

    // @PutMapping("/{id}")
    //public Reminder updateReminder(@PathVariable Long id, @RequestBody Reminder reminderDetails) {
        //Reminder reminder = reminderRepository.findById(id).orElse(null);
    //}
}
