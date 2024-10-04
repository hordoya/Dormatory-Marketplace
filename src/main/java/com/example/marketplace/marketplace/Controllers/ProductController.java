package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.ProductRepository;
import com.example.marketplace.marketplace.Services.CustomerService;
import com.example.marketplace.marketplace.Services.FileStorageService;
import com.example.marketplace.marketplace.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class ProductController {
    @Autowired
    private final ProductService productService;
    private final CustomerService customerService;
    private final FileStorageService fileStorageService;
    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, CustomerService customerService, FileStorageService fileStorageService, ProductRepository productRepository) {
        this.productService = productService;
        this.customerService = customerService;
        this.fileStorageService = fileStorageService;
        this.productRepository = productRepository;
    }

    @GetMapping("/product/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/product/add")
    public String addProduct(@AuthenticationPrincipal UserDetails userDetails,
                             @ModelAttribute("product") Product product,
                             @RequestParam("photo") MultipartFile photo,
                             RedirectAttributes redirectAttributes) {

        User user = this.customerService.findByUsername(userDetails.getUsername());
        if (!photo.isEmpty()) {
            String fileUrl = this.fileStorageService.saveFile(photo);
            product.setPhotoUrl(fileUrl);
        }
        product.setUser(user);
        this.productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Product product = this.productService.findProductById(id);
        if (product == null) {
            return "redirect:/"; // Handle product not found case
        }
        model.addAttribute("product", product);
        return "product-details";
    }

}
