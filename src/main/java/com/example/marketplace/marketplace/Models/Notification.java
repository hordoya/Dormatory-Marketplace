package com.example.marketplace.marketplace.Models;

import jakarta.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipient;  // The seller who will receive the notification

    private String message;
    private boolean isRead;

    // Setters and Getters
    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Corrected setRead method
    public void setRead(boolean isRead) {
        this.isRead = isRead;  // Set the actual field isRead
    }

    // Other necessary getters and setters...
    public User getRecipient() {
        return this.recipient;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isRead() {
        return this.isRead;
    }

    public Long getId() {
        return this.id;
    }
}
