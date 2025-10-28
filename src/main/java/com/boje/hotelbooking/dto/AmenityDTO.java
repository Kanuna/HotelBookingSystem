package com.boje.hotelbooking.dto;

import com.boje.hotelbooking.models.Hotel;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AmenityDTO {
    @NotBlank(message = "Amenity must have a name.")
    private String name;

    private List<Hotel> hotels;
}