package com.marketcore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

/**
 * Contrôleur pour surveiller l'utilisation de la mémoire en temps réel
 * Accessible via /api/memory/stats
 */
@RestController
@RequestMapping("/api/memory")
public class MemoryMonitorController {

    @GetMapping("/stats")
    public Map<String, Object> getMemoryStats() {
        Runtime runtime = Runtime.getRuntime();
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();

        Map<String, Object> stats = new HashMap<>();

        // Mémoire JVM
        stats.put("totalMemoryMB", runtime.totalMemory() / (1024 * 1024));
        stats.put("freeMemoryMB", runtime.freeMemory() / (1024 * 1024));
        stats.put("usedMemoryMB", (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
        stats.put("maxMemoryMB", runtime.maxMemory() / (1024 * 1024));

        // Heap Memory
        Map<String, Long> heap = new HashMap<>();
        heap.put("usedMB", heapUsage.getUsed() / (1024 * 1024));
        heap.put("committedMB", heapUsage.getCommitted() / (1024 * 1024));
        heap.put("maxMB", heapUsage.getMax() / (1024 * 1024));
        stats.put("heap", heap);

        // Non-Heap Memory
        Map<String, Long> nonHeap = new HashMap<>();
        nonHeap.put("usedMB", nonHeapUsage.getUsed() / (1024 * 1024));
        nonHeap.put("committedMB", nonHeapUsage.getCommitted() / (1024 * 1024));
        stats.put("nonHeap", nonHeap);

        // Pourcentage d'utilisation
        double usagePercent = ((double) (runtime.totalMemory() - runtime.freeMemory()) / runtime.maxMemory()) * 100;
        stats.put("usagePercent", String.format("%.2f%%", usagePercent));

        // Threads actifs
        stats.put("activeThreads", Thread.activeCount());

        return stats;
    }

    @GetMapping("/gc")
    public Map<String, String> triggerGarbageCollection() {
        System.gc();
        return Map.of(
            "status", "success",
            "message", "Garbage Collection déclenchée"
        );
    }
}

