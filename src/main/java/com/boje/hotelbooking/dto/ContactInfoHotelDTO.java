package com.boje.hotelbooking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactInfoHotelDTO {
    private String hotelEmail;
    private String hotelPhoneNumber;

    List<ManagerDTO> managers;
}