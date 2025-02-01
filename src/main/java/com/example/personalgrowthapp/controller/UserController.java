package com.example.personalgrowthapp.controller;

import com.example.personalgrowthapp.model.User;
import com.example.personalgrowthapp.model.Role;
import com.example.personalgrowthapp.model.Goal;
import com.example.personalgrowthapp.service.UserService;
import com.example.personalgrowthapp.service.GoalService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Třída UserController slouží jako kontroler pro správu uživatelů.
 * Obsahuje REST API pro CRUD operace a také frontendovou část pro zobrazení seznamu uživatelů.
 */
@Controller
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;
    private final GoalService goalService;

    public UserController(UserService userService, GoalService goalService) {
        this.userService = userService;
        this.goalService = goalService;
    }

    // ✅ Zobrazení seznamu uživatelů (HTML stránka)
    @GetMapping
    public String showUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("goals", goalService.getAllGoals()); // Přidání seznamu cílů
        return "users"; // Odkazuje na users.html v šablonách
    }

    // ✅ API pro získání všech uživatelů (AJAX volání z frontendové stránky)
    @GetMapping("/api")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Zajistí, že se zobrazí login.html v `templates/`
    }

    /**
     * Získá uživatele podle ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Vytvoří nového uživatele přes REST API.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail(), Role.ROLE_USER);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Aktualizuje existujícího uživatele.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        try {
            User savedUser = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Smaže uživatele podle ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userService.deleteUser(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Zobrazí seznam uživatelů pro frontend.
     */
    @GetMapping("/list")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    /**
     * Zobrazí formulář pro vytvoření nového uživatele.
     */
    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    /**
     * Zpracuje vytvoření uživatele z formuláře.
     */
    @PostMapping("/new")
    public String createUser(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password) {
        try {
            userService.registerUser(username, password, email, Role.ROLE_USER);
        } catch (IllegalArgumentException e) {
            return "redirect:/users/new?error=true";
        }
        return "redirect:/users";
    }

    /**
     * Přidá cíl konkrétnímu uživateli.
     */
    @PostMapping("/{userId}/add-goal")
    public String assignGoalToUser(@PathVariable Long userId, @RequestParam Long goalId) {
        Goal goal = goalService.findById(goalId)
                .orElseThrow(() -> new IllegalArgumentException("Cíl nenalezen."));
        userService.assignGoalToUser(userId, goal);
        return "redirect:/users";
    }

    /**
     * Odebere cíl od uživatele.
     */
    @PostMapping("/{userId}/remove-goal/{goalId}")
    public String removeGoalFromUser(@PathVariable Long userId, @PathVariable Long goalId) {
        userService.removeGoalFromUser(userId, goalId);
        return "redirect:/users";
    }
}