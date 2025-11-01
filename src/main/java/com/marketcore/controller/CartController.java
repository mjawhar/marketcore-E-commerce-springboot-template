package com.marketcore.controller;

import com.marketcore.dto.CartLine;
import com.marketcore.model.DeliveryMode;
import com.marketcore.model.Product;
import com.marketcore.service.CartService;
import com.marketcore.service.DeliveryModeService;
import com.marketcore.service.ProductService;
import com.marketcore.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;
    private final DeliveryModeService deliveryModeService;

    @PostMapping("/cart/add/{productId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> add(@PathVariable("productId") Long productId,
                      @RequestParam(name="qty", defaultValue="1") int qty,
                      HttpSession session){
        // explore this in complete version
    }

    // Conserver l'ancienne méthode pour la compatibilité
    @PostMapping("/cart/add-redirect/{productId}")
    public String addWithRedirect(@PathVariable("productId") Long productId,
                      @RequestParam(name="qty", defaultValue="1") int qty,
                      HttpSession session){
        // explore this in complete version
    }

    @GetMapping("/cart")
    public String view(Model model, HttpSession session){
        // explore this in complete version
    }

    @GetMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") Long id, HttpSession session){
        if(userService.getCurrentUser() != null){
            cartService.removeItem(id);
        }else{
            Map<Long, CartLine> guest = (Map<Long, CartLine>) session.getAttribute("guestCart");
            if(guest != null){
                guest.remove(id);
            }
        }
        return "redirect:/cart";
    }
    
    @PostMapping("/cart/update-delivery-cost")
    @ResponseBody
    public ResponseEntity<?> updateDeliveryCost(@RequestParam("deliveryMethod") String deliveryMethodCode) {
        ...
    }

    @GetMapping("/cart/count")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getCartCount(HttpSession session) {
        int count = 0;

        ..
        }

        return ResponseEntity.ok(Map.of("count", count));
    }

    @PostMapping("/cart/update/{id}")
    public String updateQuantity(@PathVariable("id") Long id,
                                 @RequestParam("action") String action,
                                 HttpSession session) {
        if(userService.getCurrentUser() != null) {
            if ("increase".equals(action)) {
                cartService.increaseQuantity(id);
            } else if ("decrease".equals(action)) {
                cartService.decreaseQuantity(id);
            }
        } else {
            Map<Long, CartLine> guest = (Map<Long, CartLine>) session.getAttribute("guestCart");
            if (guest != null && guest.containsKey(id)) {
                CartLine line = guest.get(id);
                if ("increase".equals(action)) {
                    line.setQty(line.getQty() + 1);
                } else if ("decrease".equals(action)) {
                    if (line.getQty() > 1) {
                        line.setQty(line.getQty() - 1);
                    } else {
                        guest.remove(id);
                    }
                }
            }
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/update-ajax/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateQuantityAjax(@PathVariable("id") Long id,
                                                                  @RequestParam("action") String action,
                                                                  HttpSession session) {
        // explore this in complete version
    }
}
