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
    private String picture;
    private String Seller;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product() {
    }

    public Product(String name, String description, Double price, String picture, String sellerName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.Seller = sellerName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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


}
