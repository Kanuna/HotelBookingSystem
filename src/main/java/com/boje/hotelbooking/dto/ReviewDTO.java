package com.boje.hotelbooking.dto;

import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewDTO {
    @NotBlank(message = "Title can't be empty.")
    private String title;

    @NotBlank(message = "Comment can't be empty.")
    private String comment;
    private double rating;
    private LocalDate createdAt;

    private User user;
    private Hotel hotel;
}