package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.Notification;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Services.CustomerService;
import com.example.marketplace.marketplace.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/notifications")
    public String viewNotifications(Model model, Principal principal) {
        String username = principal.getName();
        User seller = this.userService.findByUsername(username);
        List<Notification> notifications = this.notificationService.getNotificationsForUser(seller);

        model.addAttribute("notifications", notifications);
        return "view-notifications";
    }
}
