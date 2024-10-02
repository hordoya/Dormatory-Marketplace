package com.example.marketplace.marketplace.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Customer")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    private String userName;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain at least one digit, one letter, and one special character")
    private String password;

    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;

    @OneToMany(mappedBy = "user")
    private List<Product> products;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    private int matriculationNumber;

    public long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public Cart getCart() {
        return this.cart;
    }

    public Address getAddress() {
        return this.address;
    }

    public Address setAddress(Address address) {
        return this.address = address;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public int getMatriculationNumber() {
        return this.matriculationNumber;
    }

    public List<Product> getProducts() {
        return this.products;
    }

}
