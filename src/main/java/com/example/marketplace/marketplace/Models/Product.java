package com.example.marketplace.marketplace.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Changed from String to Long

    private String name;
    private String description;
    private Double price;
    private String picture;
    private String SellerName;

    public Product() {

    }

    public Product(String name, String description, Double price, String picture, String sellerName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.SellerName = sellerName;
    }
}
