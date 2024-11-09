package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Product;

import java.util.List;

public interface ProductService {

    void softDeleteProduct(Long productId);

    Product findProductById(Long id);

    void saveProduct(Product product);

    void deleteProduct(Long productId);

    List<Product> findAll();

    public void deleteProductById(Long productId);

    // Fetch all non-deleted products
    List<Product> findAllActiveProducts();

    void placeOrderAndDeleteProducts(List<Long> productIds);
}
