package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.Order;
import com.example.marketplace.marketplace.Models.OrderItem;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NotificationService notificationService;


    @Override
    public void placeOrder(User buyer, List<CartItem> cartItems) {
        Order order = new Order();
        order.setBuyer(buyer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Placed");

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);

            // Send notification to the seller
            User seller = cartItem.getProduct().getSeller();
            String message = "Your product '" + cartItem.getProduct().getName() + "' has been ordered.";
            this.notificationService.sendNotification(seller, message);
        }

        order.setOrderItems(orderItems);
        this.orderRepository.save(order);
    }


}
