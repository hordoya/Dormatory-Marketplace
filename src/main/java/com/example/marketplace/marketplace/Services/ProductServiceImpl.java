package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Repos.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    //    @Autowired
    private ProductRepository productRepository;


    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll(); // Implement this method
    }
}
