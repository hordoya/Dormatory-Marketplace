package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Models.UserDTO;

import java.util.List;

public interface CustomerService {
    void saveUser(User user);

    User findByUsername(String username);

    UserDTO getUserDetails(User user);


    List<User> findAll();

    User findById(Long userId);

    void deleteUserById(Long id);
}
