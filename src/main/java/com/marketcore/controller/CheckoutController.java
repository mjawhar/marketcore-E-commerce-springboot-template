package com.marketcore.controller;

import com.marketcore.dto.CartLine;
import com.marketcore.dto.CheckoutForm;
import com.marketcore.model.Address;
import com.marketcore.model.Product;
import com.marketcore.model.User;
import com.marketcore.model.enums.AddressType;
import com.marketcore.service.AddressService;
import com.marketcore.service.CartService;
import com.marketcore.service.DeliveryModeService;
import com.marketcore.service.OrderService;
import com.marketcore.service.ProductService;
import com.marketcore.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;
    private final ProductService productService;
    private final DeliveryModeService deliveryModeService;
    private final AddressService addressService;

    @GetMapping("/checkout")
    public String checkoutForm(Model model, HttpSession session){
        // explore this in complete version
    }

    @PostMapping("/checkout")
    public String place(@Valid @ModelAttribute("form") CheckoutForm form,
                        BindingResult result,
                        Model model,
                        RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            User currentUser = userService.getCurrentUser();

            // Récupérer les items du panier
            var cartItems = cartService.getCartItems();

            // Calculer manuellement le total pour s'assurer qu'il est correct
            ....

        User currentUser = userService.getCurrentUser();

        // Vérifier si l'utilisateur n'avait pas d'adresses avant la commande
        List<Address> existingAddresses = addressService.getUserAddresses(currentUser);
        boolean hadNoAddresses = existingAddresses.isEmpty();

    }
}
