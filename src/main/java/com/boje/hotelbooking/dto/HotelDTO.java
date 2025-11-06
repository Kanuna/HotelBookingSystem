package com.boje.hotelbooking.dto;

import com.boje.hotelbooking.models.ContactInfoHotel;
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

    private List<Integer> room_ids;
    private int vacantRoomsCount;
    private int totalRoomsCount;

    private List<Integer> review_ids;
    private double averageRating;
    private List<Integer> amenity_ids;
}