package com.boje.hotelbooking.dto;

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

    private AddressDTO address;
    private ContactInfoHotelDTO contactInfoHotel;

    private List<RoomDTO> rooms;
    private int vacantRoomsCount;
    private int totalRoomsCount;

    private List<ReviewDTO> reviews;
    private double averageRating;
    private List<AmenityDTO> amenities;
    private List<ManagerDTO> managers;
}