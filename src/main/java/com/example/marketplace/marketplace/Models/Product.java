package com.example.marketplace.marketplace.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Changed from String to Long
    private String name;
    private String description;
    private Double price;
    //    private String picture;
    private String Seller;
    private Integer quantity;
    private Double discount;
    private String tags;
    private Boolean available = true;
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Product() {
    }

    public Product(String name, String description, Double price, String picture, String sellerName, Integer quantity, Double discount, String photoUrl, String tags, Boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.Seller = sellerName;
        this.quantity = quantity;
        this.discount = discount;
        this.photoUrl = photoUrl;
        this.available = available;


    }

    private boolean inCart;

    // Getter and setter for inCart
    public boolean isInCart() {
        return this.inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeller(User sellerName) {
        this.user = sellerName;
    }

    public User getSeller() {
        return this.user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
