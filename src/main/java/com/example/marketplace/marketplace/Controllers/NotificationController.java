package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.Notification;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Services.CustomerService;
import com.example.marketplace.marketplace.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CustomerService userService;

    //    @GetMapping("/notifications")
//    public String viewNotifications(Model model, Principal principal) {
//        String username = principal.getName();
//        User seller = this.userService.findByUsername(username);
//        List<Notification> notifications = this.notificationService.getNotificationsForUser(seller);
//
//        model.addAttribute("notifications", notifications);
//        return "view-notifications";
//    }
    @GetMapping("/notifications")
    public String viewNotifications(Model model, Principal principal, @AuthenticationPrincipal UserDetails currentUser) {
        String username = principal.getName();
        User seller = this.userService.findByUsername(username);
        List<Notification> notifications = this.notificationService.getNotificationsForUser(seller);

        // Debug: Log notifications
        notifications.forEach(notification -> {
            System.out.println("Notification ID: " + notification.getId());
            System.out.println("Transaction: " + notification.getTransaction());
            if (notification.getTransaction() != null) {
                System.out.println("Seller Confirmed: " + notification.getTransaction().getSellerConfirmed());
                System.out.println("Buyer Confirmed: " + notification.getTransaction().getBuyerConfirmed());
            }
        });

        model.addAttribute("notifications", notifications);
        model.addAttribute("currentUser", currentUser);
        return "view-notifications";
    }

    @GetMapping("/buyer/notifications")
    public String viewBuyerNotifications(Model model, Principal principal) {
        String username = principal.getName();
        User buyer = this.userService.findByUsername(username);
        List<Notification> notifications = this.notificationService.getNotificationsForUser(buyer);

        // Debug: Log notifications
        notifications.forEach(notification -> {
            System.out.println("Notification ID: " + notification.getId());
            System.out.println("Transaction: " + notification.getTransaction());
            if (notification.getTransaction() != null) {
                System.out.println("Seller Confirmed: " + notification.getTransaction().getSellerConfirmed());
                System.out.println("Buyer Confirmed: " + notification.getTransaction().getBuyerConfirmed());
            }
        });

        model.addAttribute("notifications", notifications);
        return "buyer-notifications";  // Make sure the view name matches your HTML template
    }

}
