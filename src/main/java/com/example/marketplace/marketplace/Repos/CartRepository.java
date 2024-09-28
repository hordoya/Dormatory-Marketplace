package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.Cart;
import com.example.marketplace.marketplace.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    //Cart findByUser(User user);
    Optional<Cart> findByUser(User user);
}
