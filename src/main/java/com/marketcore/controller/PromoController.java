package com.marketcore.controller;

import com.marketcore.service.ProductService;
import com.marketcore.config.SiteConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PromoController {

    private final ProductService productService;
    private final SiteConfig siteConfig;

    @GetMapping("/promo")
    public String promoPage(Model model) {
        // explore this in complete version
    }
}
