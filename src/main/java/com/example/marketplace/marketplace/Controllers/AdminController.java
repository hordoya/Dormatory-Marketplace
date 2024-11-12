package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.UserRepository;
import com.example.marketplace.marketplace.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<User> users = this.customerService.findAll(); // Fetch all users from the database
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        this.customerService.deleteUserById(id);  // Call the service to delete the user
        return "redirect:/admin/dashboard";  // Redirect back to the dashboard after deletion
    }

    @GetMapping("/admin/user/{userId}/activities")
    public String showUserActivities(@PathVariable Long userId, Model model) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<Product> boughtProducts = this.customerService.findBoughtProductsByUser(user);
        List<Product> soldProducts = this.customerService.findSoldProductsByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("boughtProducts", boughtProducts);
        model.addAttribute("soldProducts", soldProducts);

        return "user_activity";  // Renders the user_activity.html template
    }
}
