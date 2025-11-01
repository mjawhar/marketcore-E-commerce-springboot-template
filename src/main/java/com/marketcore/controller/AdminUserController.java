package com.marketcore.controller;

import com.marketcore.model.Role;
import com.marketcore.model.User;
import com.marketcore.repository.RoleRepository;
import com.marketcore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String listUsers(@RequestParam(value = "q", required = false) String q,
                           @RequestParam(value = "role", required = false) String role,
                           Model model) {
        // explore this in complete version
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // explore this in complete version
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute User user,
                          BindingResult bindingResult,
                          @RequestParam(value = "roleIds", required = false) List<Long> roleIds,
                          @RequestParam(value = "passwordConfirm", required = false) String passwordConfirm,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("successMessage", "Utilisateur supprimé avec succès");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Utilisateur non trouvé");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de la suppression: " + e.getMessage());
        }

        return "redirect:/admin/users";
    }

    @PostMapping("/toggle-status/{id}")
    public String toggleUserStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Utilisateur non trouvé");
            return "redirect:/admin/users";
        }

        User user = userOpt.get();
        // Ici vous pourriez ajouter un champ 'active' ou 'enabled' au modèle User si nécessaire
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "Statut de l'utilisateur modifié");
        return "redirect:/admin/users";
    }
}
