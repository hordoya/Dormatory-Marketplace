package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);

    List<Product> findAllByUserNot(User user);

    List<Product> findAllByUserNotAndInCartFalse(User user);


}
