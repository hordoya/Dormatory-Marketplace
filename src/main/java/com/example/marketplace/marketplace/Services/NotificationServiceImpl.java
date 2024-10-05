package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Notification;
import com.example.marketplace.marketplace.Models.Transaction;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.NotificationRepository;
import com.example.marketplace.marketplace.Repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public void sendNotification(User recipient, String message, Transaction transaction) {
        Transaction savedTransaction = this.transactionRepository.save(transaction);
        Notification notification = new Notification();
        notification.setRecipient(recipient);
//        Transaction transaction = new Transaction();
        notification.setMessage(message);
        notification.setTransaction(transaction);
        notification.setRead(false);  // Notification is unread initially
        this.notificationRepository.save(notification);
    }


    public List<Notification> getNotificationsForUser(User user) {
        return this.notificationRepository.findByRecipient(user);
    }


}
