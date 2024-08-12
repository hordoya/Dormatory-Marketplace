package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Product;

import java.util.List;

public interface ProductService {


    void saveProduct(Product product);

    List<Product> findAll();
}
