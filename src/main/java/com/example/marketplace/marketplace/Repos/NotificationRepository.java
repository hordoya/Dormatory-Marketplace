package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.Notification;
import com.example.marketplace.marketplace.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipient(User recipient);
}
