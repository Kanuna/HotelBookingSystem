package com.boje.hotelbooking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactInfoHotelDTO {
    private String hotelEmail;
    private String hotelPhoneNumber;

    private List<Integer> manager_ids;
    private Integer hotel_id;
}