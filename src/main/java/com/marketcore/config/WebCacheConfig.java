package com.marketcore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration pour optimiser la mise en cache des ressources statiques
 * Améliore les performances et le SEO en réduisant les temps de chargement
 */
//@Configuration
public class WebCacheConfig implements WebMvcConfigurer {

    private static final int CACHE_PERIOD_IMAGES = 60 * 60 * 24 * 7; // 7 jours en secondes
    private static final int CACHE_PERIOD_RESOURCES = 60 * 60 * 24; // 1 jour en secondes
    private static final int CACHE_PERIOD_FAVICON = 60 * 60 * 24 * 30; // 30 jours en secondes

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configuration du cache pour les images
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .setCachePeriod(CACHE_PERIOD_IMAGES);

        // Configuration du cache pour les ressources CSS/JS
        registry.addResourceHandler("/css/**", "/js/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/js/")
                .setCachePeriod(CACHE_PERIOD_RESOURCES);

        // Configuration du cache pour les images statiques (logo, bannières, etc.)
        registry.addResourceHandler("/img/**", "/banner/**")
                .addResourceLocations("classpath:/static/img/", "classpath:/static/banner/")
                .setCachePeriod(CACHE_PERIOD_IMAGES);

        // Configuration du cache pour les favicons
        registry.addResourceHandler("/favicon.ico", "/favicon.png", "/favicon.webp")
                .addResourceLocations("classpath:/static/favicon.ico", "classpath:/static/img/favicon.png", "classpath:/static/img/favicon.webp")
                .setCachePeriod(CACHE_PERIOD_FAVICON);
    }
}