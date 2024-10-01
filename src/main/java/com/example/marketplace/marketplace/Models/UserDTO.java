package com.example.marketplace.marketplace.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private LocalDate birthday;
    private int matriculationNumber;
    private List<Product> products;
    private Cart cart;

    // Constructor
    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.matriculationNumber = user.getMatriculationNumber();
        this.products = user.getProducts();
        this.cart = user.getCart();
    }
}

