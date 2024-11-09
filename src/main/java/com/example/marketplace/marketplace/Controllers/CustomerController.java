package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Models.UserDTO;
import com.example.marketplace.marketplace.Repos.ProductRepository;
import com.example.marketplace.marketplace.Repos.UserRepository;
import com.example.marketplace.marketplace.Services.CartService;
import com.example.marketplace.marketplace.Services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class CustomerController {


    @Autowired
    private CustomerService userService;
    @Autowired
    private CartService cartService;
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
                System.out.println(error.getDefaultMessage());
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
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = this.userRepository.findByUserName(username);

        // Fetch all products not added by the logged-in user
        List<Product> products = this.productRepository.findAllByUserNot(user);

        // Fetch the products in the user's cart
        List<CartItem> cartItems = this.cartService.findCartItemsByUser(user);
        List<Long> cartProductIds = cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getId())
                .toList(); // Get a list of product IDs in the cart

        // Filter out products that are already in the cart
        List<Product> availableProducts = products.stream()
                .filter(product -> !cartProductIds.contains(product.getId()))
                .toList();

        model.addAttribute("products", availableProducts);
        return "productsHtml"; // This view will display the products
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = this.userService.getUserDetails(user);
        model.addAttribute("user", userDTO);
        return "user-details";
    }
}