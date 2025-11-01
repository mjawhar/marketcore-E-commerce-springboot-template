package com.marketcore.controller;

import com.marketcore.service.CartService;
import com.marketcore.service.UserService;
import com.marketcore.config.SiteConfig;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final CartService cartService;
    private final UserService userService;
    private final SiteConfig siteConfig;

    /**
     * Ajoute le nombre d'articles dans le panier pour toutes les vues
     */
    @ModelAttribute("cartItemCount")
    public Integer cartItemCount(HttpSession session) {
        ...

    /**
     * Ajoute le nom du site pour toutes les vues
     */
    @ModelAttribute("siteName")
    public String siteName() {
        return siteConfig.getSiteName();
    }

    /**
     * Ajoute la description du site pour toutes les vues
     */
    @ModelAttribute("siteDescription")
    public String siteDescription() {
        return siteConfig.getSiteDescription();
    }

    /**
     * Ajoute le numéro de téléphone du site pour toutes les vues
     */
    @ModelAttribute("sitePhone")
    public String sitePhone() {
        return siteConfig.getSitePhone();
    }

    /**
     * Ajoute l'URL de base du site pour toutes les vues
     */
    @ModelAttribute("baseUrl")
    public String baseUrl() {
        return siteConfig.getBaseUrl();
    }

    /**
     * Ajoute le code de devise pour toutes les vues
     */
    @ModelAttribute("currencyCode")
    public String currencyCode() {
        return siteConfig.getCurrencyCode();
    }

    /**
     * Ajoute le symbole de devise pour toutes les vues
     */
    @ModelAttribute("currencySymbol")
    public String currencySymbol() {
        return siteConfig.getCurrencySymbol();
    }

    /**
     * Ajoute le taux de change pour toutes les vues
     */
    @ModelAttribute("currencyRate")
    public Double currencyRate() {
        return siteConfig.getCurrencyRate();
    }

    /**
     * Ajoute le pays du site pour toutes les vues
     */
    @ModelAttribute("siteCountry")
    public String siteCountry() {
        return siteConfig.getSiteCountry();
    }

    /**
     * Ajoute la ville du site pour toutes les vues
     */
    @ModelAttribute("siteCity")
    public String siteCity() {
        return siteConfig.getSiteCity();
    }

    /**
     * Ajoute l'email du site pour toutes les vues
     */
    @ModelAttribute("siteEmail")
    public String siteEmail() {
        return siteConfig.getSiteEmail();
    }
}
