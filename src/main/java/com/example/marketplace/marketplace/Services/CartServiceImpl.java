package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.CartItem;
import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.CartItemRepository;
import com.example.marketplace.marketplace.Repos.CartRepository;
import com.example.marketplace.marketplace.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.marketplace.marketplace.Models.Cart.cartItems;

@Service
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    public CartServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void saveToCart(CartItem cartItem) {
        this.cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> findCartItemsByUser(User user) {
        return this.cartItemRepository.findByCartUser(user);
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
