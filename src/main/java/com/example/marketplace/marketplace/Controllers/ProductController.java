package com.example.marketplace.marketplace.Controllers;

import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    @Autowired
    private ProductService productService;
    //    public final ProductService productService;
    private List<Product> products;

    public ProductController() {
//        this.productService = productService1;
//        this.productService = productService;
        this.products = new ArrayList<>();
        this.products.add(new Product("Fan", "valo", 22.4));
    }

    @GetMapping("/product")
    public String findAll(Model model) {
//        List<Product> products = this.productService.findAll();
//        List<Product> products = this.productService.findAll();
        model.addAttribute("products", this.products);
        return "productsHtml";
    }

    @GetMapping("/product/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct"; // Thymeleaf template for the form
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product) {
//        List<Product> products = productService.findAll();

        this.productService.saveProduct(product); // Save the product using the service
        return "redirect:/productsHtml";
    }
}
