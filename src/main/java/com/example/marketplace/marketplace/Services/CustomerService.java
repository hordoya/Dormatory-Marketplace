package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Models.UserDTO;

public interface CustomerService {
    void saveUser(User user);

    User findByUsername(String username);

    UserDTO getUserDetails(User user);


}
