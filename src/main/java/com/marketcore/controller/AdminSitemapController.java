package com.marketcore.controller;

import com.marketcore.service.SitemapSchedulerService;
import com.marketcore.service.SitemapService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/sitemap")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminSitemapController {

    private final SitemapService sitemapService;
    private final SitemapSchedulerService sitemapSchedulerService;

    /**
     * Page d'administration du sitemap
     */
    @GetMapping
    public String sitemapPage(Model model) {
        try {
            // Récupérer les statistiques du sitemap
            ..

            return "admin/sitemap";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors du chargement des statistiques : " + e.getMessage());
            return "admin/sitemap";
        }
    }

    /**
     * Régénérer manuellement le sitemap
     */
    @PostMapping("/regenerate")
    public String regenerateSitemap(RedirectAttributes redirectAttributes) {
        ...
        return "redirect:/admin/sitemap";
    }

    /**
     * Prévisualiser le contenu du sitemap
     */
    @GetMapping("/preview")
    @ResponseBody
    public String previewSitemap() {
        return sitemapService.generateSitemap();
    }

    /**
     * Télécharger le sitemap au format XML
     */
    @GetMapping("/download")
    @ResponseBody
    public String downloadSitemap() {
        return sitemapService.generateSitemap();
    }

    /**
     * API pour obtenir les statistiques du sitemap en JSON
     */
    @GetMapping("/stats")
    @ResponseBody
    public SitemapSchedulerService.SitemapStats getSitemapStats() {
        return sitemapSchedulerService.generateSitemapStats();
    }
}
