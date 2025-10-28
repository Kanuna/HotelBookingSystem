package com.boje.hotelbooking.dto;

import com.boje.hotelbooking.models.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelDTO {
    @NotBlank(message = "Hotel must have a name.")
    private String name;
    private String description;
    private String policies;
    @NotBlank(message = "Hotel must have a franchise.")
    private String franchise;
    private double starRating;

    private Address address;
    private ContactInfoHotel contactInfoHotel;

    private List<Room> rooms;
    private int vacantRoomsCount;
    private int totalRoomsCount;

    private List<Review> reviews;
    private double averageRating;
    private List<Amenity> amenities;
    private List<Manager> managers;
}