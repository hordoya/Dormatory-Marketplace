package com.example.marketplace.marketplace.Models;

import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Boolean sellerConfirmed = false;
    private Boolean buyerConfirmed = false;

    // Method to check if both seller and buyer have confirmed
    public boolean isTransactionComplete() {
        return this.sellerConfirmed && this.buyerConfirmed;
    }

    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSeller() {
        return this.seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getSellerConfirmed() {
        return this.sellerConfirmed;
    }

    public void setSellerConfirmed(Boolean sellerConfirmed) {
        this.sellerConfirmed = sellerConfirmed;
    }

    public Boolean getBuyerConfirmed() {
        return this.buyerConfirmed;
    }

    public void setBuyerConfirmed(Boolean buyerConfirmed) {
        this.buyerConfirmed = buyerConfirmed;
    }
}

