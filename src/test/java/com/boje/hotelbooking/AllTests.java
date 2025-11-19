package com.boje.hotelbooking;

import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.repositories.*;
import com.boje.hotelbooking.serviceImp.BookingServiceImp;
import com.boje.hotelbooking.serviceImp.HotelServiceImp;
import com.boje.hotelbooking.serviceImp.RoomServiceImp;
import com.boje.hotelbooking.serviceImp.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.boje.hotelbooking.models.*;
import com.boje.hotelbooking.dto.*;
import com.boje.hotelbooking.dtoRequest.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class AllTests {
    /*@Mock
    private UserRepository userRepository;
    @Mock
    private ContactInfoRepository contactInfoRepository;
    @Mock
    private ContactInfoHotelRepository contactInfoHotelRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private UserServiceImp userService;
    @InjectMocks
    private BookingServiceImp bookingService;
    @InjectMocks
    private HotelServiceImp hotelService;
    @InjectMocks
    private RoomServiceImp roomService;

    private User user;
    private UserDTO userDTO;;
    private UserDTORequest userDTORequest;

    private Booking booking;
    private BookingDTO bookingDTO;
    private BookingDTORequest bookingDTORequest;

    private Hotel hotel;
    private HotelDTO hotelDTO;
    private HotelDTORequest hotelDTORequest;

    private Room room;
    private RoomDTO roomDTO;
    private RoomDTORequest  roomDTORequest;

    private Address address;
    private AddressDTO addressDTO;
    private AddressDTORequest addressDTORequest;

    private ContactInfoHotel contactInfoHotel;
    private ContactInfoHotelDTO contactInfoHotelDTO;
    private ContactInfoHotelDTORequest contactInfoHotelDTORequest;

    private ContactInfo contactInfo;
    private ContactInfoDTO contactInfoDTO;
    private ContactInfoDTORequest contactInfoDTORequest;

    private List<Review> reviews;
    private List<Booking> bookings;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setAge(20);
        user.setFullName("John Doe");
        user.setPassword("password");
        user.setReviews(reviews);
        user.setBookings(bookings);
        user.setContactInfo(contactInfo);

        userDTO = new UserDTO();
        userDTO.setAge(20);
        userDTO.setFullName("John Doe");
        userDTO.setPassword("password");
        userDTO.setReview_ids(Collections.emptyList());
        userDTO.setReview_ids(Collections.emptyList());
        userDTO.setContactInfo(contactInfoDTO);

        userDTORequest = new UserDTORequest();
        userDTORequest.setAge(20);
        userDTORequest.setFullName("John Doe");
        userDTORequest.setPassword("password");
        userDTORequest.setReview_ids(Collections.emptyList());
        userDTORequest.setReview_ids(Collections.emptyList());
        userDTORequest.setContactInfo(contactInfoDTO);

        booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setStartDate(LocalDate.now());
        booking.setEndDate(LocalDate.now());

        bookingDTO = new BookingDTO();
        bookingDTORequest = new BookingDTORequest();

        hotel = new Hotel();
        hotelDTO = new HotelDTO();
        hotelDTORequest = new HotelDTORequest();

        address = new Address();
        addressDTO = new AddressDTO();
        addressDTORequest = new AddressDTORequest();

        contactInfoHotel = new ContactInfoHotel();
        contactInfoHotelDTO = new ContactInfoHotelDTO();
        contactInfoHotelDTORequest = new ContactInfoHotelDTORequest();

        contactInfo = new ContactInfo();
        contactInfoDTO = new ContactInfoDTO();
        contactInfoDTORequest = new ContactInfoDTORequest();

        room = new Room();
        roomDTO = new RoomDTO();
        roomDTORequest = new RoomDTORequest();
    }*/
}