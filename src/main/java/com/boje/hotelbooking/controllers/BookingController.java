package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.BookingDTO;
import com.boje.hotelbooking.dtoRequest.BookingDTORequest;
import com.boje.hotelbooking.serviceImp.BookingServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingServiceImp bookingServiceImp;

    public  BookingController(BookingServiceImp bookingServiceImp) {
        this.bookingServiceImp = bookingServiceImp;
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingDTORequest> createBooking(@RequestBody BookingDTORequest bookingDTORequest){
        BookingDTORequest createdBooking = bookingServiceImp.createBooking(bookingDTORequest);
        URI location = URI.create(String.format("/bookings/%d",createdBooking.getId()));

        return  ResponseEntity.created(location).body(createdBooking);
    }

    @PutMapping("/booking")
    public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTORequest bookingDTORequest){
        BookingDTO  updatedBooking = bookingServiceImp.updateBooking(bookingDTORequest);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int bookingId){
        bookingServiceImp.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/bookings")
    public ResponseEntity<List<BookingDTO>> getBookingsByUserId(@PathVariable int userId){
        List<BookingDTO> bookings = bookingServiceImp.getByBookingsUser_Id(userId);
        return ResponseEntity.ok(bookings);
    }
}