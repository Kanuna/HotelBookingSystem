package com.boje.hotelbooking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {
    @NotNull(message = "Start date can't be null.")
    private LocalDate startDate;
    @NotNull(message = "End date can't be null.")
    private LocalDate endDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotNull(message = "Booking must have a room.")
    private Integer room_id;
    @NotNull(message = "Booking must have a user.")
    private Integer user_id;
}