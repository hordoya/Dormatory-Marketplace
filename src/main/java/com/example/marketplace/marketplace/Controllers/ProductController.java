package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Services.CustomerService;
import com.example.marketplace.marketplace.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    @Autowired
    private final ProductService productService;
    private final CustomerService customerService;

    public ProductController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        return "productsHtml";
    }

    @GetMapping("/product/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct"; // Thymeleaf template for the form
    }

//    @PostMapping("/product/add")
//    public String addProduct(@ModelAttribute("product") Product product) {
//        this.productService.saveProduct(product);
//        return "redirect:/product";
//    }

    @PostMapping("/product/add")
    public String addProduct(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("product") Product product) {
        User user = this.customerService.findByUsername(userDetails.getUsername()); // Get the user from the database
        product.setUser(user); // Set the user for the product
        this.productService.saveProduct(product); // Save the product
        return "redirect:/";

    }
}
