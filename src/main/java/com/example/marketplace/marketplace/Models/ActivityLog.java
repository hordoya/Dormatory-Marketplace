package com.example.marketplace.marketplace.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // The user performing the action

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller; // The seller involved in the activity, if applicable

    public void setUser(User user) {
        this.user = user;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTimestamp(LocalDateTime now) {
        this.timestamp = now;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}

