package com.boje.hotelbooking.dto;

import com.boje.hotelbooking.models.ContactInfoHotel;
import com.boje.hotelbooking.models.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    Hotel hotel;
    private ContactInfoHotel contactInfoHotel;
}