package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.Cart;
import com.example.marketplace.marketplace.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);

}
