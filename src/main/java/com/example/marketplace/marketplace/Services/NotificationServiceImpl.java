package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Notification;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(User recipient, String message) {
        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setMessage(message);
        notification.setRead(false);  // Notification is unread initially
        this.notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForUser(User user) {
        return this.notificationRepository.findByRecipient(user);
    }

    
}
