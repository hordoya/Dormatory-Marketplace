package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Product;

import java.util.List;

public interface ProductService {
    Object findProductById(Long id);

    void saveProduct(Product beverage);
    List<Product> findAll();
}
