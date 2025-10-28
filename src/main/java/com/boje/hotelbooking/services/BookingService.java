package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dtoRequest.BookingDTORequest;
import com.boje.hotelbooking.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTORequest createBooking(BookingDTORequest bookingDTORequest);
    BookingDTORequest updateBooking(BookingDTORequest bookingDTORequest);
    boolean deleteBooking(int booking_id);
    List<BookingDTO> getByUser_Id(int user_id);
}
