package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.*;
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

    @Autowired
    private ActivityLogService activityLogService;

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

            // Send notifications
            User seller = cartItem.getProduct().getSeller();
            String sellerMessage = "Your product '" + cartItem.getProduct().getName() + "' has been ordered.";
            this.notificationService.sendNotification(seller, sellerMessage, new Transaction());

            String buyerMessage = "You have successfully ordered the product '" + cartItem.getProduct().getName() + "'.";
            this.notificationService.sendNotification(buyer, buyerMessage, new Transaction());

            // Log activity for the buyer and include the seller
            this.activityLogService.logActivity(buyer, "Ordered product '" + cartItem.getProduct().getName() + "'.", seller);
        }

        order.setOrderItems(orderItems);
        this.orderRepository.save(order);

        // Additional log for the order itself
        this.activityLogService.logActivity(buyer, "Placed an order with ID: " + order.getId(), null);
    }

}
