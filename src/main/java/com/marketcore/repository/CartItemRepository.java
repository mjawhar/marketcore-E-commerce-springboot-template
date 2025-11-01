package com.marketcore.repository;

import com.marketcore.model.CartItem;
import com.marketcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart_User(User buyer);
}
