package com.example.marketplace.marketplace.Controllers;


import com.example.marketplace.marketplace.Models.Cart;
import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Services.CartService;
import com.example.marketplace.marketplace.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;


    @PostMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable Long productId, Model model, @RequestParam("quantity") int quantity) {
        Product product = (Product) this.productService.findProductById(productId);
        if (product != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            this.cartService.saveToCart(cartItem);
        }
        return "redirect:/";
    }


    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<CartItem> cartItems = this.cartService.findAllProductsInCart();
        Cart cart = new Cart();
        cart.setCartItems(cartItems);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cart", cart);
//        model.addAttribute("totalAmount", cart.calculateTotalAmount());
        return "view-cart";
    }

//    @PostMapping("/cart/delete/{itemId}")
//    public String deleteCartItem(@PathVariable Long itemId) {
//        this.cartService.deleteCartItemById(itemId);
//        return "redirect:/cart";
//    }


}


