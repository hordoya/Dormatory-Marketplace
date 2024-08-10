package com.example.marketplace.marketplace.Controllers;
import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String findAll(Model model)
    {
        List<Product> products = productService.findAll();

        model.addAttribute("products", products);
        return "home";
    }
}
