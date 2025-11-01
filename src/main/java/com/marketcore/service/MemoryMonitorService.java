package com.marketcore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Service de monitoring automatique de la mémoire
 * Surveille l'utilisation mémoire toutes les 5 minutes et alerte si > 80%
 */
@Service
public class MemoryMonitorService {

    private static final Logger logger = LoggerFactory.getLogger(MemoryMonitorService.class);
    private static final double MEMORY_THRESHOLD = 0.80; // 80%

    /**
     * Vérifie l'utilisation mémoire toutes les 5 minutes
     */
    @Scheduled(fixedDelay = 300000) // 5 minutes
    public void monitorMemory() {
        // explore this in complete version
    }

    /**
     * Statistiques de threads toutes les 10 minutes
     */
    @Scheduled(fixedDelay = 600000) // 10 minutes
    public void monitorThreads() {
        // explore this in complete version
    }
}
