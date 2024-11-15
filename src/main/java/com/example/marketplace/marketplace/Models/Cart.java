package com.example.marketplace.marketplace.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

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

    //    @OneToMany(cascade = CascadeType.ALL)
//    public static Collection<CartItem> cartItems;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    public static Collection<CartItem> cartItems = new HashSet<>(); // Initialize cartItems to avoid NullPointerException


    public static Collection<CartItem> getItems() {
        return cartItems;
    }

    public void setCartItems(Collection<CartItem> cartItems) {
        Cart.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<CartItem> getCartItems() {
        return cartItems;
    }


    // Add methods to manage cart items
}
