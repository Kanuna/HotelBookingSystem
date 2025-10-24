package com.boje.hotelbooking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.List;

@Getter
@Setter
@Table(name = "rooms")
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private byte numberOfBeds;

    @Column(nullable = false)
    private boolean hasKitchen;

    @Column(nullable = false)
    private short roomSize;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Occupied occupied;


    public enum Occupied{
        OCCUPIED,
        VACANT
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;
}
