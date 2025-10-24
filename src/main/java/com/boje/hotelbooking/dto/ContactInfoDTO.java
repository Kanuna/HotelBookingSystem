package com.boje.hotelbooking.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfoDTO {
    @Email
    private String email;
    @NotBlank(message = "Phone number can't be blank")
    private String phone;
}