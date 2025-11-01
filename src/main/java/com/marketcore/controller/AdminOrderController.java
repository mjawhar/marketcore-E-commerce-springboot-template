package com.marketcore.controller;

import com.marketcore.model.enums.OrderStatus;
import com.marketcore.model.enums.PaymentStatus;
import com.marketcore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderRepository orderRepository;

    @GetMapping
    public String list(@RequestParam(value="status", required=false) OrderStatus status,
                       @RequestParam(value="from", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                       @RequestParam(value="to", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                       Model model){
        // explore this in complete version
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model){
        // explore this in complete version
    }

    @PostMapping("/status/{id}")
    public String changeStatus(@PathVariable Long id, @RequestParam("status") OrderStatus status){
        // explore this in complete version
    }

    @PostMapping("/payment/{id}")
    public String changePayment(@PathVariable Long id, @RequestParam("payment") PaymentStatus payment){
        // explore this in complete version
    }
}
