package com.boje.hotelbooking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "manager")
@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Manager must have a name.")
    private String fullName;

    @Column(nullable = false)
    @NotBlank(message = "Manager must have an email.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Manager must have a phone number.")
    private String phoneNumber;


    @OneToMany(mappedBy = "managers")
    private List<Hotel> hotel;

    @ManyToOne
    @JoinColumn(name = "contact_info_hotel_id", nullable = false)
    private ContactInfoHotel contactInfoHotel;
}