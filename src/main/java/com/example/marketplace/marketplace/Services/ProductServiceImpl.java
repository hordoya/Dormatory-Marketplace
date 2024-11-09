package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.Product;
import com.example.marketplace.marketplace.Repos.CartItemRepository;
import com.example.marketplace.marketplace.Repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public ProductServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

//    @Override
//    public Product findProductById(Long id) {
//        return this.productRepository.findById(id).orElse(null);
//    }

    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        //List<Product> cartItems = this.findAllProductsInCartForUser(user);
        this.cartItemRepository.deleteByProductId(productId);
        this.productRepository.deleteByProductId(productId);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll(); // Implement this method
    }

    public void deleteProductById(Long productId) {
        this.productRepository.deleteById(productId);

    }

    @Override
    public void placeOrderAndDeleteProducts(List<Long> productIds) {
        // Delete the products from the cart and the database
        productIds.forEach(productId -> {
            // First, delete the product's cart items if necessary
            this.cartItemRepository.deleteByProductId(productId);
            // Then, delete the product itself
            this.productRepository.deleteById(productId);
        });
    }


    @Override
    public void softDeleteProduct(Long productId) {
        Product product = this.productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setDeleted(true);
            this.productRepository.save(product);
        }
    }

    @Override
    public Product findProductById(Long productId) {
        Product product = this.productRepository.findById(productId).orElse(null);
        if (product != null && !product.isDeleted()) {
            return product;
        }
        return null;
    }

    @Override
    public List<Product> findAllActiveProducts() {
        return this.productRepository.findAll().stream()
                .filter(product -> !product.isDeleted())
                .toList();
    }
}
