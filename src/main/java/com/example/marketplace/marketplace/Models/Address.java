package com.example.marketplace.marketplace.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Address")
@Setter
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Street is required")
    private String street;

    @NotNull(message = "City is required")
    private String city;


    @NotNull(message = "Room Number is required")
    private Integer roomNumber;

    @NotNull(message = "Postal Code is required")
    private String postalCode;

    @NotNull(message = "Country is required")
    private String country;

    // Getters and Setters
}