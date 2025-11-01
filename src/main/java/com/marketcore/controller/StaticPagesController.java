package com.marketcore.controller;

import com.marketcore.config.SiteConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StaticPagesController {

    private final SiteConfig siteConfig;

    @GetMapping("/contact")
    public String contact(Model model) {
        // explore this in complete version
    }

    @GetMapping("/about")
    public String about(Model model) {
        // explore this in complete version
    }

    @GetMapping("/conditions-generales")
    public String conditionsGenerales(Model model) {
        // explore this in complete version
    }
}
