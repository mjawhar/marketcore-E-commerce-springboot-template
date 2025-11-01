package com.marketcore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Contrôleur avancé pour analyser QUELS objets sont en mémoire
 * et identifier les fuites mémoire
 */
@RestController
@RequestMapping("/api/memory/analysis")
public class MemoryAnalysisController {

    /**
     * Affiche les instances d'objets présentes en mémoire
     * ATTENTION: Opération coûteuse, à utiliser uniquement pour debug
     */
    @GetMapping("/objects")
    public Map<String, Object> getObjectsInMemory() {
        // explore this in complete version
    }

    /**
     * Analyse détaillée des threads actifs
     * Les threads non fermés sont une cause fréquente de fuites mémoire
     */
    @GetMapping("/threads")
    public Map<String, Object> analyzeThreads() {
        // explore this in complete version
    }

    /**
     * Analyse du Garbage Collection
     */
    @GetMapping("/gc")
    public Map<String, Object> analyzeGarbageCollection() {
        Map<String, Object> gcInfo = new HashMap<>();

        // Informations sur les GC
        ManagementFactory.getGarbageCollectorMXBeans().forEach(gc -> {
            Map<String, Object> gcDetails = new HashMap<>();
            gcDetails.put("collectionCount", gc.getCollectionCount());
            gcDetails.put("collectionTimeMs", gc.getCollectionTime());
            gcDetails.put("valid", gc.isValid());
            gcInfo.put(gc.getName(), gcDetails);
        });

        return gcInfo;
    }

    /**
     * Diagnostic complet - À utiliser quand la RAM augmente sans raison
     */
    @GetMapping("/diagnostic")
    public Map<String, Object> fullDiagnostic() {
        Map<String, Object> diagnostic = new HashMap<>();

        // 1. Mémoire
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        long maxMemory = runtime.maxMemory();
        double usagePercent = (double) usedMemory / maxMemory * 100;

        Map<String, Object> memory = new HashMap<>();
        memory.put("usedMB", usedMemory / (1024 * 1024));
        memory.put("totalMB", runtime.totalMemory() / (1024 * 1024));
        memory.put("maxMB", maxMemory / (1024 * 1024));
        memory.put("usagePercent", String.format("%.2f%%", usagePercent));
        memory.put("status", usagePercent > 80 ? "🔴 CRITIQUE" : usagePercent > 60 ? "🟡 ATTENTION" : "🟢 OK");
        diagnostic.put("memory", memory);

        // 2. Threads
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        Map<String, Object> threads = new HashMap<>();
        threads.put("active", Thread.activeCount());
        threads.put("peak", threadBean.getPeakThreadCount());
        threads.put("status", Thread.activeCount() > 100 ? "🔴 Trop de threads" : "🟢 OK");
        diagnostic.put("threads", threads);

        // 3. Classes
        ClassLoadingMXBean classBean = ManagementFactory.getClassLoadingMXBean();
        Map<String, Object> classes = new HashMap<>();
        classes.put("loaded", classBean.getLoadedClassCount());
        classes.put("unloaded", classBean.getUnloadedClassCount());
        classes.put("status", classBean.getLoadedClassCount() > 15000 ? "🟡 Beaucoup de classes" : "🟢 OK");
        diagnostic.put("classes", classes);

        // 4. Recommandations
        List<String> recommendations = new ArrayList<>();
        if (usagePercent > 80) {
            recommendations.add("⚠️ Mémoire critique ! Déclencher un GC : curl -X GET /api/memory/force-gc");
        }
        if (Thread.activeCount() > 100) {
            recommendations.add("⚠️ Trop de threads actifs. Vérifier les connexions non fermées");
        }
        if (classBean.getLoadedClassCount() > 15000) {
            recommendations.add("⚠️ Beaucoup de classes chargées. Possible fuite de classloader");
        }

        if (recommendations.isEmpty()) {
            recommendations.add("✅ Tout semble normal");
        }

        diagnostic.put("recommendations", recommendations);
        diagnostic.put("timestamp", new Date());

        return diagnostic;
    }

    /**
     * Force le Garbage Collection
     * Utile pour voir si la mémoire est vraiment utilisée ou juste pas nettoyée
     */
    @GetMapping("/force-gc")
    public Map<String, Object> forceGarbageCollection() {
        Runtime runtime = Runtime.getRuntime();
        long beforeUsed = runtime.totalMemory() - runtime.freeMemory();

        // Force GC multiple fois pour être sûr
        System.gc();
        System.runFinalization();
        System.gc();

        try {
            Thread.sleep(1000); // Attendre que GC se termine
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long afterUsed = runtime.totalMemory() - runtime.freeMemory();
        long freedMemory = beforeUsed - afterUsed;

        Map<String, Object> result = new HashMap<>();
        result.put("beforeUsedMB", beforeUsed / (1024 * 1024));
        result.put("afterUsedMB", afterUsed / (1024 * 1024));
        result.put("freedMemoryMB", freedMemory / (1024 * 1024));
        result.put("status", freedMemory > 0 ? "✅ Mémoire libérée" : "⚠️ Aucune mémoire libérée (possible fuite)");
        result.put("message", freedMemory > 0
            ? "Le GC a libéré de la mémoire, c'était juste de la mémoire non nettoyée"
            : "Le GC n'a rien libéré, la mémoire est vraiment utilisée (possible fuite)");

        return result;
    }

    /**
     * Compare la mémoire avant/après pour identifier les fuites
     */
    @GetMapping("/leak-detection")
    public Map<String, Object> detectMemoryLeak() {
        Map<String, Object> detection = new HashMap<>();

        // Mesure 1
        Runtime runtime = Runtime.getRuntime();
        long measure1 = runtime.totalMemory() - runtime.freeMemory();
        int threads1 = Thread.activeCount();
        int classes1 = ManagementFactory.getClassLoadingMXBean().getLoadedClassCount();

        detection.put("instruction", "Appelez cet endpoint plusieurs fois (espacées de 5 minutes) et comparez les résultats");
        detection.put("timestamp", new Date());
        detection.put("usedMemoryMB", measure1 / (1024 * 1024));
        detection.put("activeThreads", threads1);
        detection.put("loadedClasses", classes1);

        detection.put("howToDetectLeak", Map.of(
            "step1", "Appelez /api/memory/leak-detection",
            "step2", "Attendez 5 minutes avec activité utilisateur",
            "step3", "Appelez à nouveau /api/memory/leak-detection",
            "step4", "Si usedMemoryMB augmente sans redescendre = FUITE MÉMOIRE",
            "step5", "Si activeThreads augmente = FUITE DE THREADS",
            "step6", "Si loadedClasses augmente = FUITE DE CLASSLOADER"
        ));

        return detection;
    }
}
