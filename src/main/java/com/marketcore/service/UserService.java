package com.marketcore.service;

import com.marketcore.dto.RegisterRequest;
import com.marketcore.model.User;

public interface UserService {
    User registerSeller(RegisterRequest dto);
    User registerBuyer(RegisterRequest dto);
    User register(RegisterRequest dto); // Nouvelle méthode unifiée pour l'inscription
    User getCurrentUser();
    boolean emailExists(String email);
    User findByEmail(String email);
    User findById(Long id); // Nouvelle méthode pour trouver un utilisateur par ID
    void autoLogin(User user, String rawPassword);
    User updateUserProfile(User user, String fullName, String phoneNumber, String marquee);
    User save(User user); // Nouvelle méthode pour sauvegarder un utilisateur
}
