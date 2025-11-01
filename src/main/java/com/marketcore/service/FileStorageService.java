package com.marketcore.service;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FileStorageService {

    private final Path bucketPath;

    // Configurations pour optimisation adaptative
    // Pour écrans Retina/4K : on garde 1920px (bon compromis qualité/poids)
    private static final int MAX_WIDTH = 1920; // Compatible Retina et Full HD
    private static final int MAX_HEIGHT = 1920; // Compatible Retina et Full HD
    private static final float COMPRESSION_QUALITY = 0.88f; // 88% = excellente qualité

    public FileStorageService(@Value("${CC_FS_BUCKET:/app/bucket}") String ccFsBucket) {
        // Extraire le chemin du bucket depuis CC_FS_BUCKET (avant le premier :)
        String mountPath = ccFsBucket.split(":")[0];
        this.bucketPath = Paths.get(mountPath);

        try {
            // Vérifier et créer le dossier du bucket s'il n'existe pas
            if (!Files.exists(bucketPath)) {
                Files.createDirectories(bucketPath);
                log.debug("Dossier bucket créé: {}", bucketPath); // Changé de INFO à DEBUG
            }

        } catch (IOException e) {
            log.error("Erreur lors de la création du dossier bucket: {}", bucketPath, e);
        }
    }

    public String storeFile(MultipartFile file) throws IOException {
        // explore this in complete version
        return null;
    }

    /**
     * Vérifie si le fichier est une image
     */
    private boolean isImageFile(String extension) {
        // explore this in complete version
        return false;
    }

    /**
     * Optimise et sauvegarde l'image en streaming (économe en mémoire)
     * - Redimensionne uniquement si > 1920px (garde qualité Retina)
     * - Compresse avec qualité 88% (quasi-imperceptible)
     * - Utilise le streaming pour éviter de charger toute l'image en RAM
     */
    private void optimizeAndSaveImage(InputStream inputStream, Path outputPath) throws IOException {
        // explore this in complete version
    }

    public byte[] loadFileAsBytes(String filename) throws IOException {
        // explore this in complete version
        return null;
    }

    public String getFilePath(String filename) {
        // explore this in complete version
        return null;
    }

    public void deleteFile(String filename) throws IOException {
        // explore this in complete version
    }
}
