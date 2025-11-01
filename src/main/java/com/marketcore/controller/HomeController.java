package com.marketcore.controller;

import com.marketcore.model.Category;
import com.marketcore.repository.CategoryRepository;
import com.marketcore.service.ProductService;
import com.marketcore.service.FeaturedProductService;
import com.marketcore.config.SiteConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final FeaturedProductService featuredProductService;
    private final SiteConfig siteConfig;

    @GetMapping({"", "/"})
    public String home(Model model){
        // explore this in complete version
    }
}
