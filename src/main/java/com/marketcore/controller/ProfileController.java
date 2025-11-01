package com.marketcore.controller;

import com.marketcore.model.Address;
import com.marketcore.model.Order;
import com.marketcore.model.User;
import com.marketcore.model.enums.AddressType;
import com.marketcore.repository.OrderRepository;
import com.marketcore.service.AddressService;
import com.marketcore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final AddressService addressService;

    @GetMapping
    public String showProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // explore this in complete version
    }

    @PostMapping("/address")
    public String addOrUpdateAddress(@AuthenticationPrincipal UserDetails userDetails,
                                   @Valid @ModelAttribute("newAddress") Address address,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @PostMapping("/address/{id}/delete")
    public String deleteAddress(@AuthenticationPrincipal UserDetails userDetails,
                              @PathVariable Long id,
                              RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @GetMapping("/address/{id}/edit")
    @ResponseBody
    public Address getAddressForEdit(@AuthenticationPrincipal UserDetails userDetails,
                                   @PathVariable Long id) {
        // explore this in complete version
    }

    @PostMapping("/update")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String fullName,
                               @RequestParam String phoneNumber,
                               @RequestParam(required = false) String marquee,
                               @RequestParam(required = false) String facebookUrl,
                               @RequestParam(required = false) String instagramUrl,
                               RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }
}
