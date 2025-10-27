package com.boje.hotelbooking.dto;

import com.boje.hotelbooking.models.Booking;
import com.boje.hotelbooking.models.ContactInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private byte age;
    @NotBlank(message = "User must have a name/full name.")
    private String fullName;
    @NotBlank(message = "User must have a password.")
    private String password;

    private List<Booking> bookings;
    private ContactInfo contactInfo;
}