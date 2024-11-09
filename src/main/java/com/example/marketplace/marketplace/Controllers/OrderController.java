package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Services.CartService;
import com.example.marketplace.marketplace.Services.CustomerService;
import com.example.marketplace.marketplace.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/placeOrder")
    public String placeOrder(Principal principal, Model model) {
        String username = principal.getName();
        User buyer = this.customerService.findByUsername(username);

        List<CartItem> cartItems = this.cartService.findAllProductsInCartForUser(buyer);

        if (!cartItems.isEmpty()) {
            this.orderService.placeOrder(buyer, cartItems);
            this.cartService.clearCartForUser(buyer);  // Clear the cart after placing the order
        }

        model.addAttribute("message", "Your order has been placed successfully!");
        return "order-confirmation";  // Create this view to display order confirmation
    }
}