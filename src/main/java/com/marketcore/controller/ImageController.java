package com.marketcore.controller;

import com.marketcore.model.Product;
import com.marketcore.service.FileStorageService;
import com.marketcore.service.ImageOptimizationService;
import com.marketcore.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ProductService productService;
    private final FileStorageService fileStorageService;
    private final ImageOptimizationService imageOptimizationService;

    @Value("${CC_FS_BUCKET:/app/bucket}")
    private String uploadDir;

    /**
     * Endpoint principal pour servir les images (version adaptative selon appareil)
     * Sert automatiquement la bonne taille selon mobile/tablette/desktop
     * Cache HTTP activé pour éviter de recharger les images
     */
    @GetMapping("/images/{id}/{idx}")
    public ResponseEntity<byte[]> img(
            @PathVariable Long id,
            @PathVariable int idx,
            @RequestHeader(value = "User-Agent", required = false) String userAgent,
            @RequestHeader(value = "Sec-CH-Viewport-Width", required = false) Integer viewportWidth) throws IOException {
        // explore this in complete version
    }

    /**
     * Détecte si l'appareil est mobile via User-Agent
     */
    private boolean isMobileDevice(String userAgent) {
        // explore this in complete version
    }

    /**
     * Détecte si l'appareil est une tablette via User-Agent
     */
    private boolean isTabletDevice(String userAgent) {
        if (userAgent == null) return false;
        String ua = userAgent.toLowerCase();
        return ua.contains("ipad") ||
                ua.contains("tablet") ||
                ua.contains("android") && !ua.contains("mobile");
    }

    /**
     * Endpoint pour miniatures (160x160) - TRÈS LÉGER
     * Utilise un cache sur disque pour ne pas régénérer à chaque fois
     */
    @GetMapping("/images/{id}/{idx}/thumbnail")
    public ResponseEntity<byte[]> thumbnail(@PathVariable Long id, @PathVariable int idx) throws IOException {
        return getOptimizedImage(id, idx, "thumbnail", ImageOptimizationService.THUMBNAIL_SIZE);
    }

    /**
     * Endpoint pour cartes de produits (400x400) - LÉGER
     */
    @GetMapping("/images/{id}/{idx}/card")
    public ResponseEntity<byte[]> cardImage(@PathVariable Long id, @PathVariable int idx) throws IOException {
        return getOptimizedImage(id, idx, "card", ImageOptimizationService.CARD_SIZE);
    }

    /**
     * Endpoint pour taille medium (800x800) - MOYEN
     */
    @GetMapping("/images/{id}/{idx}/medium")
    public ResponseEntity<byte[]> mediumImage(@PathVariable Long id, @PathVariable int idx) throws IOException {
        return getOptimizedImage(id, idx, "medium", ImageOptimizationService.MEDIUM_SIZE);
    }

    /**
     * Endpoint pour grande taille (1200x1200) - DESKTOP
     */
    @GetMapping("/images/{id}/{idx}/large")
    public ResponseEntity<byte[]> largeImage(@PathVariable Long id, @PathVariable int idx) throws IOException {
        return getOptimizedImage(id, idx, "large", ImageOptimizationService.LARGE_SIZE);
    }

    /**
     * Endpoint pour pages de détail (1920x1920) - HAUTE QUALITÉ
     */
    @GetMapping("/images/{id}/{idx}/detail")
    public ResponseEntity<byte[]> detailImage(@PathVariable Long id, @PathVariable int idx) throws IOException {
        return getOptimizedImage(id, idx, "detail", ImageOptimizationService.DETAIL_SIZE);
    }

    /**
     * Méthode générique pour servir des images optimisées avec CACHE SUR DISQUE
     * Évite de consommer de la RAM en régénérant les images à chaque fois
     */
    private ResponseEntity<byte[]> getOptimizedImage(Long productId, int imageIdx, String sizeType, int size) throws IOException {
        // explore this in complete version
        return null;
    }

    /**
     * Charge les données d'une image
     */
    private byte[] loadImageData(Product p, int idx) throws IOException {
        String imagePath = switch (idx) {
            case 1 -> p.getImage1Path();
            case 2 -> p.getImage2Path();
            case 3 -> p.getImage3Path();
            default -> null;
        };

        if (imagePath != null && !imagePath.isEmpty()) {
            return fileStorageService.loadFileAsBytes(imagePath);
        }
        return null;
    }

    /**
     * Récupère le type MIME d'une image
     */
    private String getImageType(Product p, int idx) {
        return switch (idx) {
            case 1 -> p.getImage1Type();
            case 2 -> p.getImage2Type();
            case 3 -> p.getImage3Type();
            default -> "image/jpeg";
        };
    }
}
