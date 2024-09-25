package com.example.marketplace.marketplace.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    public static Collection<CartItem> cartItems;

    public static Collection<CartItem> getItems() {
        return cartItems;
    }

    public void setCartItems(Collection<CartItem> cartItems) {
        Cart.cartItems = cartItems;
    }

    // Add methods to manage cart items
}
