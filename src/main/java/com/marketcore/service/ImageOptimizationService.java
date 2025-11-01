package com.marketcore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Service pour optimiser les images existantes sur le serveur
 * Traite les images une par une pour économiser la RAM
 * Supporte la génération de multiples tailles pour images responsives
 * OPTIMISÉ POUR FAIBLE CONSOMMATION MÉMOIRE
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ImageOptimizationService {

    // Tailles prédéfinies pour images responsives
    public static final int THUMBNAIL_SIZE = 160;  // Pour miniatures/cartes mobiles
    public static final int CARD_SIZE = 400;       // Pour cartes de produits
    public static final int MEDIUM_SIZE = 800;     // Pour tablettes
    public static final int LARGE_SIZE = 1200;     // Pour desktop
    public static final int DETAIL_SIZE = 1920;    // Pour pages de détail

    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1920;
    private static final float COMPRESSION_QUALITY = 0.85f;  // Réduit à 85% pour meilleure compression
    private static final long MIN_FILE_SIZE_KB = 200; // Optimiser uniquement si > 200KB

    @Value("${CC_FS_BUCKET:/app/bucket}")
    private String ccFsBucket;

    /**
     * Optimise toutes les images existantes dans le bucket
     * Traitement séquentiel pour économiser la RAM
     */
    public OptimizationResult optimizeExistingImages() {
        String mountPath = ccFsBucket.split(":")[0];
        Path bucketPath = Paths.get(mountPath);

        OptimizationResult result = new OptimizationResult();

        log.info("🖼️  Début de l'optimisation des images dans: {}", bucketPath);

        try (Stream<Path> paths = Files.walk(bucketPath, 1)) {
            paths.filter(Files::isRegularFile)
                 .filter(this::isImageFile)
                 .forEach(imagePath -> {
                     try {
                         optimizeImage(imagePath, result);
                     } catch (Exception e) {
                         log.error("Erreur lors de l'optimisation de {}: {}", imagePath, e.getMessage());
                         result.incrementErrors();
                     }

                     // Pause courte pour éviter surcharge CPU/RAM
                     try {
                         Thread.sleep(100);
                     } catch (InterruptedException e) {
                         Thread.currentThread().interrupt();
                     }
                 });
        } catch (IOException e) {
            log.error("Erreur lors du parcours du dossier: {}", e.getMessage());
        }

        log.info("✅ Optimisation terminée: {}", result);
        return result;
    }

    /**
     * Optimise une image individuelle
     */
    private void optimizeImage(Path imagePath, OptimizationResult result) throws IOException {
        // explore this in complete version
    }

    /**
     * Vérifie si le fichier est une image
     */
    private boolean isImageFile(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        return fileName.endsWith(".jpg") ||
               fileName.endsWith(".jpeg") ||
               fileName.endsWith(".png") ||
               fileName.endsWith(".gif") ||
               fileName.endsWith(".webp");
    }

    /**
     * Crée une image thumbnail optimisée (160x160)
     * STREAMING - Ne charge PAS toute l'image en mémoire
     */
    public byte[] createThumbnail(byte[] imageData, String mimeType) throws IOException {
        // explore this in complete version
    }

    /**
     * Crée une image pour carte de produit (400x400)
     */
    public byte[] createCardImage(byte[] imageData, String mimeType) throws IOException {
        // explore this in complete version
    }

    /**
     * Crée une image medium pour tablettes (800x800)
     */
    public byte[] createMediumImage(byte[] imageData, String mimeType) throws IOException {
        // explore this in complete version
    }

    /**
     * Crée une image large pour desktop (1200x1200)
     */
    public byte[] createLargeImage(byte[] imageData, String mimeType) throws IOException {
        // explore this in complete version
    }

    /**
     * Crée une image haute résolution pour pages de détail (1920x1920)
     */
    public byte[] createDetailImage(byte[] imageData, String mimeType) throws IOException {
        // explore this in complete version
    }

    /**
     * Redimensionne et compresse une image avec qualité optimale
     * STREAMING - Utilise ByteArrayInputStream pour minimiser la consommation mémoire
     */
    private byte[] resizeAndCompress(byte[] imageData, int maxWidth, int maxHeight, float quality) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            Thumbnails.of(bis)
                .size(maxWidth, maxHeight)
                .outputQuality(quality)
                .outputFormat("jpg")
                .toOutputStream(bos);

            return bos.toByteArray();
        } finally {
            // Libérer explicitement les ressources
            bis.close();
            bos.close();
        }
    }

    /**
     * Convertit une image en WebP (format moderne plus léger)
     * Note: Nécessite une bibliothèque WebP (à ajouter si besoin)
     */
    public byte[] convertToWebP(byte[] imageData, int maxWidth, int maxHeight) throws IOException {
        // Pour l'instant, retourne une version JPEG optimisée
        // TODO: Implémenter la vraie conversion WebP avec une bibliothèque appropriée
        return resizeAndCompress(imageData, maxWidth, maxHeight, 0.80f);
    }

    /**
     * Résultat de l'optimisation
     */
    public static class OptimizationResult {
        private int totalProcessed = 0;
        private int optimized = 0;
        private int skipped = 0;
        private int errors = 0;
        private long totalSavedKB = 0;

        public void incrementTotal() { totalProcessed++; }
        public void incrementOptimized() { optimized++; }
        public void incrementSkipped() { skipped++; }
        public void incrementErrors() { errors++; }
        public void addSaved(long kb) { totalSavedKB += kb; }

        // Getters publics pour accès depuis AdminController
        public int getTotalProcessed() { return totalProcessed; }
        public int getOptimized() { return optimized; }
        public int getSkipped() { return skipped; }
        public int getErrors() { return errors; }
        public long getTotalSavedKB() { return totalSavedKB; }

        @Override
        public String toString() {
            return String.format(
                "Total: %d | Optimisées: %d | Ignorées: %d | Erreurs: %d | Économie: %d MB",
                totalProcessed, optimized, skipped, errors, totalSavedKB / 1024
            );
        }
    }
}
