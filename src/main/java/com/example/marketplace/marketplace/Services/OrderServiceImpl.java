package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Repos.OrderRepository;
import com.example.marketplace.marketplace.Repos.ProductRepository;
import jakarta.persistence.criteria.Order;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }



    @Override
    public Order createOrder(Order order) {


        // Save the order to the repository

        return this.orderRepository.save(order);
    }
}
