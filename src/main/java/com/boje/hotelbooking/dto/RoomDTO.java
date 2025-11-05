package com.boje.hotelbooking.dto;

import com.boje.hotelbooking.models.Room;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomDTO {
    @NotBlank(message = "Room must have a room number.")
    private String roomNumber;
    private byte numberOfBeds;
    private boolean hasKitchen;
    private short roomSize;
    private int price;

    private Room.Occupied occupied;

    @NotNull(message = "Room must belong to a hotel.")
    private HotelDTO hotel;
    private List<BookingDTO> bookings;

}