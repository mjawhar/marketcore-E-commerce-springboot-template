package com.marketcore.service;

/**
 * Service pour l'envoi d'emails
 */
public interface EmailService {

    /**
     * Envoie un email de réinitialisation de mot de passe
     * @param email L'adresse email du destinataire
     * @param token Le token de réinitialisation
     */
    void sendPasswordResetEmail(String email, String token);

    /**
     * Envoie un email de confirmation de commande
     * @param toEmail L'adresse email du client
     * @param orderNumber Le numéro de commande
     * @param customerName Le nom du client
     * @param totalAmount Le montant total de la commande
     * @param orderDetails Les détails de la commande
     */
    void sendOrderConfirmationEmail(String toEmail, String orderNumber, String customerName,
                                   double totalAmount, String orderDetails);
}
