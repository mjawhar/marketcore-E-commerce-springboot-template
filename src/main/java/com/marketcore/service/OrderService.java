package com.marketcore.service;

import com.marketcore.dto.CheckoutForm;
import com.marketcore.model.Order;
import com.marketcore.model.User;
import com.marketcore.model.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    Order placeOrder(CheckoutForm form); // explore this in complete version

    List<Order> findByBuyer(User buyer); // explore this in complete version

    List<Order> findOrdersBySeller(User seller); // explore this in complete version

    List<Order> findOrdersBySellerAndStatus(User seller, OrderStatus status); // explore this in complete version

    Order findById(Long id); // explore this in complete version

    Order save(Order order); // explore this in complete version
}
