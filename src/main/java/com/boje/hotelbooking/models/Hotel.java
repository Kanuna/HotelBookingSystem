package com.boje.hotelbooking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "hotel")
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String policies;

    @Column(nullable = false)
    private String franchise;

    @Column(nullable = false)
    private double starRating;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @OneToOne(cascade =  CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(cascade =  CascadeType.ALL, optional = false)
    @JoinColumn(name = "contactInfo_id", nullable = false)
    private ContactInfo contactInfo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities;



    public int getVacantRoomsCount() {
        return (int) rooms.stream()
                .filter(room -> room.getOccupied() == Room.Occupied.VACANT)
                .count();
    }

    public int getTotalRoomsCount() {
        return rooms.size();
    }

    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        for(Review review : reviews){
            totalRating += review.getRating();
        }

        return totalRating / reviews.size();
    }
}