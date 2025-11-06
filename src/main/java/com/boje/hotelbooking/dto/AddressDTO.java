package com.boje.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    @NotBlank(message = "Region can't be blank.")
    private String region;
    @NotBlank(message = "Zipcode can't be blank.")
    private short zipCode;
    @NotBlank(message = "City can't be blank.")
    private String city;
    @NotBlank(message = "Street can't be blank.")
    private String street;

    private Integer hotel_id;
}