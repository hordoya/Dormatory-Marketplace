package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.ProductRepository;
import com.example.marketplace.marketplace.Repos.UserRepository;
import com.example.marketplace.marketplace.Services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class CustomerController {


    @Autowired
    private CustomerService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


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

//    @GetMapping("/my-products")
//    public String getUserProducts(Model model) {
//        // Get the logged-in user
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        // Find user by username
//        User user = this.userRepository.findByUserName(username);
//
//        // Fetch products by user
//        List<Product> products = this.productRepository.findByUser(user);
//        model.addAttribute("products", products);
//
//        return "my-products";
//    }

    @GetMapping("/my-products")
    public String getUserProducts(Model model) {
        // Get the current logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = this.userRepository.findByUserName(username);

        // Fetch products added by this user
        List<Product> products = this.productRepository.findByUser(user);

        // Add products to the model
        model.addAttribute("products", products);
        return "my-products";
    }

    @GetMapping("/")
    public String getOtherProducts(Model model) {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Find the logged-in user in the database
        User user = this.userRepository.findByUserName(username);

        // Fetch products not added by the logged-in user
        List<Product> products = this.productRepository.findAllByUserNot(user);
        model.addAttribute("products", products);

        return "productsHtml"; // This view will display the products
    }
}
