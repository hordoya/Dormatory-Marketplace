package com.example.marketplace.marketplace.Controllers;


import com.example.marketplace.marketplace.Models.Cart;
import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Services.CartService;
import com.example.marketplace.marketplace.Services.CustomerService;
import com.example.marketplace.marketplace.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService userService;


    @PostMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable Long productId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = this.userService.findByUsername(username);  // Get the logged-in user

        Product product = (Product) this.productService.findProductById(productId);
        if (product != null && user != null) {

            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                user.setCart(cart);
            }
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);

            cartItem.setCart(cart);
            product.setInCart(true);
            this.productService.saveProduct(product);


            this.cartService.saveToCart(cartItem);
        }
        return "redirect:/";
    }


    @GetMapping("/cart")
    public String viewCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = this.userService.findByUsername(username);
        System.out.println(this.cartService.findCartItemsByUser(user));

        List<CartItem> cartItems = this.cartService.findCartItemsByUser(user);
        Cart cart = new Cart();
        cart.setCartItems(cartItems);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cart", cart);
        return "view-cart";
    }


    @PostMapping("/removeFromCart/{productId}")
    public String removeFromCart(@PathVariable Long productId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = this.userService.findByUsername(username);

        if (user != null) {
            CartItem cartItem = this.cartService.findCartItemByProductIdAndUser(productId, user);
            if (cartItem != null) {
                this.cartService.removeFromCart(cartItem);

                // Mark product as not in cart
                Product product = cartItem.getProduct();
                product.setInCart(false);
                this.productService.saveProduct(product);
            }
        }
        return "redirect:/cart";
    }
}