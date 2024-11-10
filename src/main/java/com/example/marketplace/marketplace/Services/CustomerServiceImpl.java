package com.example.marketplace.marketplace.Services;

import com.example.marketplace.marketplace.Models.User;
import com.example.marketplace.marketplace.Models.UserDTO;
import com.example.marketplace.marketplace.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username); // logic to load user details from the database

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username); //throws UsernameNotFoundException if no user found
        }
        //String role = user.getUserName().contains("admin") ? "ADMIN" : "USER";

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                //.passwordEncoder(password -> passwordEncoder().encode(password))

                .build();

        return userDetails; //if the user is found
    }

    public UserDTO getUserDetails(User user) {
        return new UserDTO(user);  // Convert User entity to UserDTO to exclude password
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }


}
