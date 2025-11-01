package com.marketcore.controller;

import com.marketcore.dto.RegisterRequest;
import com.marketcore.model.User;
import com.marketcore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        // explore this in complete version
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        // explore this in complete version
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegisterRequest dto, BindingResult result, Model model){
        // explore this in complete version
    }

    @PostMapping("/register/buyer")
    public String registerBuyer(@Valid @ModelAttribute("user") RegisterRequest dto, BindingResult result, Model model){
        // explore this in complete version
    }

    @PostMapping("/register/seller")
    public String registerSeller(@Valid @ModelAttribute("user") RegisterRequest dto, BindingResult result, Model model){
        // explore this in complete version
    }
}
