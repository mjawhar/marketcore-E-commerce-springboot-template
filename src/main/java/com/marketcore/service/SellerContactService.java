package com.marketcore.service;

import com.marketcore.repository.SellerContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerContactService {

    @Autowired
    private SellerContactRepository sellerContactRepository;

    // Sauvegarder un nouveau message de contact vendeur
    public SellerContact saveSellerContact(String name, String phone, String email, String message) {
        // explore this in complete version
    }

    // Récupérer tous les messages non traités
    public List<SellerContact> getUnprocessedContacts() {
        // explore this in complete version
    }

    // Marquer un message comme traité
    public void markAsProcessed(Long contactId) {
        // explore this in complete version
    }

    // Compter les messages non traités
    public long countUnprocessedContacts() {
        // explore this in complete version
    }

    // Récupérer les messages récents (dernières 24h)
    public List<SellerContact> getRecentContacts() {
        // explore this in complete version
    }

    // Récupérer tous les messages
    public List<SellerContact> getAllContacts() {
        // explore this in complete version
    }

    // Supprimer un message
    public void deleteContact(Long contactId) {
        // explore this in complete version
    }
}
