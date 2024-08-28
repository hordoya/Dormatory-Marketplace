package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
