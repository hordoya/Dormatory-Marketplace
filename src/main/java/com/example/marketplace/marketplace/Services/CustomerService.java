package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.User;

public interface CustomerService {
    void saveUser(User user);

    User findByUsername(String username);


}
