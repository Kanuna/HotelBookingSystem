package com.boje.hotelbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    HotelDTO hotel;
    private ContactInfoHotelDTO contactInfoHotel;
}