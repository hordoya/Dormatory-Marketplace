package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartUser(User user);

    CartItem findByProductIdAndUser(Long productId, User user);// new added

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.product.id = :productId")
        //void deleteByProductId(@Param("productId") Long productId);
    void deleteByProductId(Long productId);


}
