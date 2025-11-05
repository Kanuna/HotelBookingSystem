package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dtoRequest.BookingDTORequest;
import com.boje.hotelbooking.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTORequest createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(BookingDTORequest bookingDTORequest);
    void deleteBooking(int booking_id);
    List<BookingDTO> getByBookingsUser_Id(int user_id);
}
