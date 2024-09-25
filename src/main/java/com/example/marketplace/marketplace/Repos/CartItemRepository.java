package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
}
