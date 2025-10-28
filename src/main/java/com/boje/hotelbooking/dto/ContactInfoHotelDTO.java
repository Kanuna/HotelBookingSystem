package com.boje.hotelbooking.dto;

import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.models.Manager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactInfoHotelDTO {
    private String hotelEmail;
    private String hotelPhoneNumber;

    private List<Manager> managers;
    private Hotel  hotel;
}