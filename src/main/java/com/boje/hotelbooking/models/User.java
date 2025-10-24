package com.boje.hotelbooking.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private byte age;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;


    @OneToOne(cascade =  CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @OneToOne(cascade =  CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "contactInfo_id", nullable = false)
    private ContactInfo contactInfo;
}