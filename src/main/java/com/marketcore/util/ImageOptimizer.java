package com.marketcore.util;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Utilitaire pour optimiser les images existantes sans ImageMagick
 * Utilise Thumbnailator (économe en mémoire)
 *
 * Usage: java -cp target/classes:target/dependency/* com.marketcore.util.ImageOptimizer
 */
@Slf4j
public class ImageOptimizer {

    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1920;
    private static final float COMPRESSION_QUALITY = 0.88f;
    private static final long MIN_SIZE_KB = 200; // Optimiser seulement si > 200KB

    public static void main(String[] args) {
        String bucketPath = System.getenv("CC_FS_BUCKET");
        if (bucketPath == null || bucketPath.isEmpty()) {
            bucketPath = System.getProperty("user.home") + "/marketcore/uploads";
        } else {
            bucketPath = bucketPath.split(":")[0];
        }

        System.out.println("🖼️  Optimisation des images existantes...");
        System.out.println("⚠️  Mode économie mémoire : traitement séquentiel");
        System.out.println("📁 Chemin: " + bucketPath);
        System.out.println();

        File bucketDir = new File(bucketPath);
        if (!bucketDir.exists() || !bucketDir.isDirectory()) {
            System.err.println("❌ Le dossier n'existe pas: " + bucketPath);
            System.exit(1);
        }

        optimizeImagesInDirectory(bucketPath);
    }

    public static void optimizeImagesInDirectory(String directoryPath) {
        AtomicInteger total = new AtomicInteger(0);
        AtomicInteger optimized = new AtomicInteger(0);
        AtomicInteger skipped = new AtomicInteger(0);
        AtomicInteger errors = new AtomicInteger(0);

        Path dir = Paths.get(directoryPath);
        Path tempDir = dir.resolve("optimized_temp");

        try {
            // Créer dossier temporaire
            Files.createDirectories(tempDir);

            // Traiter les images une par une (économie mémoire)
            try (Stream<Path> paths = Files.walk(dir, 1)) {
                paths.filter(Files::isRegularFile)
                     .filter(ImageOptimizer::isImageFile)
                     .forEach(imagePath -> {
                         total.incrementAndGet();
                         processImage(imagePath, tempDir, optimized, skipped, errors);

                         // Pause pour éviter surcharge mémoire
                         try {
                             Thread.sleep(100);
                         } catch (InterruptedException e) {
                             Thread.currentThread().interrupt();
                         }
                     });
            }

            // Nettoyage
            deleteDirectory(tempDir.toFile());

            // Résumé
            System.out.println();
            System.out.println("✅ Optimisation terminée !");
            System.out.println("  Total traité: " + total.get() + " images");
            System.out.println("  Optimisées: " + optimized.get() + " images");
            System.out.println("  Ignorées: " + skipped.get() + " images");
            System.out.println("  Erreurs: " + errors.get() + " images");

        } catch (IOException e) {
            System.err.println("❌ Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processImage(Path imagePath, Path tempDir,
                                     AtomicInteger optimized,
                                     AtomicInteger skipped,
                                     AtomicInteger errors) {
        try {
            File imageFile = imagePath.toFile();
            long sizeBytes = imageFile.length();
            long sizeKb = sizeBytes / 1024;

            String filename = imagePath.getFileName().toString();

            // Optimiser uniquement si > 200KB
            if (sizeKb < MIN_SIZE_KB) {
                skipped.incrementAndGet();
                return;
            }

            System.out.println("  Optimisation: " + filename + " (" + sizeKb + "KB)...");

            // Créer fichier temporaire
            Path tempFile = tempDir.resolve(filename);

            // Optimiser avec Thumbnailator (streaming, économe en RAM)
            Thumbnails.of(imageFile)
                .size(MAX_WIDTH, MAX_HEIGHT)
                .outputQuality(COMPRESSION_QUALITY)
                .toFile(tempFile.toFile());

            long newSizeBytes = tempFile.toFile().length();
            long newSizeKb = newSizeBytes / 1024;
            long savedKb = sizeKb - newSizeKb;

            // Remplacer uniquement si gain > 10%
            if (savedKb > (sizeKb / 10)) {
                Files.delete(imagePath);
                Files.move(tempFile, imagePath);
                System.out.println("    ✓ Optimisée: " + sizeKb + "KB → " + newSizeKb + "KB (économie: " + savedKb + "KB)");
                optimized.incrementAndGet();
            } else {
                Files.deleteIfExists(tempFile);
                System.out.println("    ⊘ Gain insuffisant, image conservée");
                skipped.incrementAndGet();
            }

        } catch (Exception e) {
            System.err.println("    ✗ Erreur: " + e.getMessage());
            errors.incrementAndGet();
        }
    }

    private static boolean isImageFile(Path path) {
        String filename = path.getFileName().toString().toLowerCase();
        return filename.endsWith(".jpg") ||
               filename.endsWith(".jpeg") ||
               filename.endsWith(".png") ||
               filename.endsWith(".gif") ||
               filename.endsWith(".webp");
    }

    private static void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}

