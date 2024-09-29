package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.User;

import java.util.List;

public interface OrderService {
    //public Order createOrder(Long productId, Long buyerId);

    //Order createOrder(Long productId, Long sellerId, Long buyerId);


    public void placeOrder(User buyer, List<CartItem> cartItems);
}
