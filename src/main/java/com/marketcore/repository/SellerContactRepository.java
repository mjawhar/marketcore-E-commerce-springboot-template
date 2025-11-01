package com.marketcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SellerContactRepository extends JpaRepository<SellerContact, Long> {

    // Trouver tous les messages non traités
    List<SellerContact> findByProcessedFalseOrderByCreatedAtDesc();

    // Trouver les messages par période
    @Query("SELECT sc FROM SellerContact sc WHERE sc.createdAt BETWEEN ?1 AND ?2 ORDER BY sc.createdAt DESC")
    List<SellerContact> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Compter les messages non traités
    long countByProcessedFalse();

    // Trouver les messages récents (dernières 24h)
    @Query("SELECT sc FROM SellerContact sc WHERE sc.createdAt >= ?1 ORDER BY sc.createdAt DESC")
    List<SellerContact> findRecentContacts(LocalDateTime since);
}
