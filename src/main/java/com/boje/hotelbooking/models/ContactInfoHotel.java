package com.boje.hotelbooking.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "contactInfoHotel")
@Entity
public class ContactInfoHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Hotel contact info must have an email.")
    private String hotelEmail;

    @Column(nullable = false)
    @NotBlank(message = "Hotel contact info must have a phone number.")
    private String hotelPhoneNumber;


    @OneToMany(mappedBy = "contactInfoHotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manager> managers;
}