package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Notification;
import com.example.marketplace.marketplace.Models.User;

import java.util.List;

public interface NotificationService {
    public void sendNotification(User recipient, String message);

    public List<Notification> getNotificationsForUser(User user);
}