package com.marketcore.controller;

import com.marketcore.repository.SiteSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/settings")
@RequiredArgsConstructor
public class AdminSettingController {

    private final SiteSettingRepository repo;

    @GetMapping
    public String form(Model model){
        ..itle","Paramètres");
        return "admin/settings";
    }

    @PostMapping
    public String save(@ModelAttribute SiteSetting setting){
        setting.setId(1L);
        repo.save(setting);
        return "redirect:/admin/settings?ok";
    }
}
