package com.marketcore.repository;

import com.marketcore.model.Order;
import com.marketcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByBuyerOrderByCreatedAtDesc(User buyer);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.items oi WHERE oi.product.seller = :seller ORDER BY o.createdAt DESC")
    List<Order> findOrdersBySeller(@Param("seller") User seller);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.items oi WHERE oi.product.seller = :seller AND o.status = :status ORDER BY o.createdAt DESC")
    List<Order> findOrdersBySellerAndStatus(@Param("seller") User seller, @Param("status") com.marketcore.model.enums.OrderStatus status);
}
