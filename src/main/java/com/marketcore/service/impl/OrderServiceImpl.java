package com.marketcore.service.impl;

import com.marketcore.dto.CheckoutForm;
import com.marketcore.model.*;
import com.marketcore.model.enums.AddressType;
import com.marketcore.model.enums.OrderStatus;
import com.marketcore.model.enums.PaymentStatus;
import com.marketcore.repository.OrderItemRepository;
import com.marketcore.repository.OrderRepository;
import com.marketcore.service.AddressService;
import com.marketcore.service.CartService;
import com.marketcore.service.DeliveryModeService;
import com.marketcore.service.EmailService;
import com.marketcore.service.OrderService;
import com.marketcore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final CartService cartService;
    private final DeliveryModeService deliveryModeService;
    private final AddressService addressService;
    private final EmailService emailService;

    @Override
    @Transactional
    public Order placeOrder(CheckoutForm form) {
        // explore this in complete version
        return null;
    }

    private String buildOrderDetailsString(List<CartItem> cartItems) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Order> findByBuyer(User buyer) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Order> findOrdersBySeller(User seller) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Order> findOrdersBySellerAndStatus(User seller, OrderStatus status) {
        // explore this in complete version
        return null;
    }

    @Override
    public Order findById(Long id) {
        // explore this in complete version
        return null;
    }

    @Override
    public Order save(Order order) {
        // explore this in complete version
        return null;
    }
}
