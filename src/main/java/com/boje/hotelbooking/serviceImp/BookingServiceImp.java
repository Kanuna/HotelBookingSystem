package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.BookingDTO;
import com.boje.hotelbooking.dtoRequest.BookingDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Booking;
import com.boje.hotelbooking.models.Room;
import com.boje.hotelbooking.models.User;
import com.boje.hotelbooking.repositories.BookingRepository;
import com.boje.hotelbooking.repositories.RoomRepository;
import com.boje.hotelbooking.repositories.UserRepository;
import com.boje.hotelbooking.services.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository  userRepository;
    private final EntityMapper entityMapper;

    public BookingServiceImp(BookingRepository bookingRepository,
                             RoomRepository roomRepository,
                             UserRepository userRepository,
                             EntityMapper entityMapper) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public BookingDTORequest createBooking(BookingDTO bookingDTO) {
        Booking booking = entityMapper.toBooking(bookingDTO);

        if (bookingDTO.getRoom_id() != null) {
             Room room = roomRepository.findById(bookingDTO.getRoom_id())
                    .orElseThrow(() -> new RuntimeException("Room not found with id: " + bookingDTO.getRoom_id()));
            booking.setRoom(room);
        }

        if (bookingDTO.getUser_id() != null) {
            User user = userRepository.findById(bookingDTO.getUser_id())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingDTO.getUser_id()));
            booking.setUser(user);
        }

        Booking savedBooking = bookingRepository.save(booking);

        return entityMapper.toBookingDTORequest(savedBooking);
    }

    @Override
    public BookingDTO updateBooking(BookingDTORequest bookingDTORequest) {
        Booking booking = bookingRepository.findById(bookingDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: "  + bookingDTORequest.getId()));

        Room room = roomRepository.findById(bookingDTORequest.getRoom_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + bookingDTORequest.getRoom_id()));

        User user = userRepository.findById(bookingDTORequest.getUser_id())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + bookingDTORequest.getUser_id()));

        booking.setUpdatedAt(bookingDTORequest.getUpdatedAt());
        booking.setRoom(room);
        booking.setUser(user);
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