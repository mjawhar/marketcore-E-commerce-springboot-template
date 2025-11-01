package com.marketcore.service;

/**
 * Service pour la gestion de la réinitialisation des mots de passe
 */
public interface PasswordResetService {

    /**
     * Crée un token de réinitialisation de mot de passe et envoie un email
     * @param email L'adresse email de l'utilisateur
     */
    void createPasswordResetToken(String email);

    /**
     * Valide un token de réinitialisation de mot de passe
     * @param token Le token à valider
     * @return true si le token est valide et non expiré, false sinon
     */
    boolean validatePasswordResetToken(String token);

    /**
     * Réinitialise le mot de passe d'un utilisateur
     * @param token Le token de réinitialisation
     * @param newPassword Le nouveau mot de passe
     */
    void resetPassword(String token, String newPassword);
}
