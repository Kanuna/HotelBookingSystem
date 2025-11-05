package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.BookingDTO;
import com.boje.hotelbooking.dtoRequest.BookingDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Booking;
import com.boje.hotelbooking.repositories.BookingRepository;
import com.boje.hotelbooking.services.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepository;
    private final EntityMapper entityMapper;

    public BookingServiceImp(BookingRepository bookingRepository, EntityMapper entityMapper) {
        this.bookingRepository = bookingRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public BookingDTORequest createBooking(BookingDTO bookingDTO) {
        Booking booking = entityMapper.toBooking(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);

        return entityMapper.toBookingDTORequest(savedBooking);
    }

    @Override
    public BookingDTO updateBooking(BookingDTORequest bookingDTORequest) {
        Booking booking = bookingRepository.findById(bookingDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: "  + bookingDTORequest.getId()));

        booking.setUpdatedAt(bookingDTORequest.getUpdatedAt());
        booking.setRoom(entityMapper.toRoom(bookingDTORequest.getRoom()));
        booking.setUser(entityMapper.toUser(bookingDTORequest.getUser()));
        booking.setStartDate(bookingDTORequest.getStartDate());
        booking.setEndDate(bookingDTORequest.getEndDate());

        Booking savedBooking = bookingRepository.save(booking);

        return entityMapper.toBookingDTO(savedBooking);
    }

    @Override
    public void deleteBooking(int booking_id) {
        if (!bookingRepository.existsById(booking_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + booking_id);
        }

        bookingRepository.deleteById(booking_id);
    }

    @Override
    public List<BookingDTO> getByBookingsUser_Id(int user_id) {
        List<Booking> bookings = bookingRepository.findByUser_Id(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: "  + user_id));

        return bookings.stream()
                .map(entityMapper::toBookingDTO)
                .collect(Collectors.toList());
    }
}