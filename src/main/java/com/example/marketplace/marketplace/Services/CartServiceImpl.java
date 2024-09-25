package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Repos.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.marketplace.marketplace.Models.Cart.cartItems;

@Service
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void saveToCart(CartItem cartItem) {
        this.cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> findAllProductsInCart() {
        return this.cartItemRepository.findAll();
    }

    @Override
    public Boolean deleteCartItemById(Long id) {
        if (this.cartItemRepository.findById(id).isPresent()) {
            this.cartItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllCartItems() {
        this.cartItemRepository.deleteAll();

    }

    @Override
    public void clearCart() {
        cartItems.clear();
    }
}
