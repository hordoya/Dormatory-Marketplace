//package com.example.marketplace.marketplace.Models;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//@Getter
//@Setter
//@Entity
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Long buyerId;
//    private Long sellerId;
//
//    @OneToOne
//    private Product product;
//
//    private LocalDateTime orderDate;
//    public Order() {
//        this.orderDate = LocalDateTime.now();
//    }
//
//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getBuyerId() {
//        return buyerId;
//    }
//
//    public void setBuyerId(Long buyerId) {
//        this.buyerId = buyerId;
//    }
//
//    public Long getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(Long sellerId) {
//        this.sellerId = sellerId;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public LocalDateTime getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(LocalDateTime orderDate) {
//        this.orderDate = orderDate;
//    }
//}
