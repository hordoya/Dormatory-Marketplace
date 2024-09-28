package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    void saveToCart(CartItem cartItem);

    List<CartItem> findAllProductsInCart();

    Boolean deleteCartItemById(Long id);

    void deleteAllCartItems();

    void clearCart();

    List<CartItem> findCartItemsByUser(User user);


}