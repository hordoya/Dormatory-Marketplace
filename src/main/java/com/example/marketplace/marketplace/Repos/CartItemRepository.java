package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartUser(User user);

    CartItem findByProductIdAndUser(Long productId, User user);// new added


}
