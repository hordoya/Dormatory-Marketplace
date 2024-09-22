package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.UserRepository;
import com.example.marketplace.marketplace.Services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CustomerController {


    @Autowired
    private CustomerService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage()); // Log validation errors
            });
            return "register";
        }

        this.userService.saveUser(user);

        return "login";
    }

    @GetMapping("/login")
    public String showlogin(Model model) {

        return "login";
    }

    @GetMapping("/logout")
    public String handleLogout(HttpServletRequest request) {

//        this.cartService.clearCart();
//        this.cartService.deleteAllCartItems();
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }

}
