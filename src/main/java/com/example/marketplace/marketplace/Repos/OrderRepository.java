package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Define any additional query methods here if needed
}
