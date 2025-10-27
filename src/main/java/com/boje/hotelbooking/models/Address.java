package com.boje.hotelbooking.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "address")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private short zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;



    @OneToOne(mappedBy = "address", optional = false)
    private Hotel hotel;
}