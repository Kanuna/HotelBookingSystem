package com.boje.hotelbooking.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private int id;
    private int age;
    private String fullName;
    private ContactInfoDTO contactInfo;
    private List<BookingDTO> bookings;
    private List<ReviewDTO> reviews;
}