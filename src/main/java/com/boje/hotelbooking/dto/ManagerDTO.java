package com.boje.hotelbooking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ManagerDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    List<HotelDTO> hotels;
    private ContactInfoHotelDTO contactInfoHotelDTO;
}