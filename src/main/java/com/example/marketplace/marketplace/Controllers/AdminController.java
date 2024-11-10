package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<User> users = this.customerService.findAll(); // Fetch all users from the database
        model.addAttribute("users", users);
        return "admin";
    }

}
