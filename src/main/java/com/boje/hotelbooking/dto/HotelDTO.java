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
    private ContactInfoDTO contactInfo;

    private List<Integer> roomIds;
    private int vacantRoomsCount;
    private int totalRoomsCount;

    private List<Integer> reviewIds;
    private double averageRating;

    private List<Integer> amenityIds;
}