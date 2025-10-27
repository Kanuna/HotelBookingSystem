package com.boje.hotelbooking.dto;

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

    private List<Integer> bookings_ids;
    private int contactInfo_id;
}