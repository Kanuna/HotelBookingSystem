package com.boje.hotelbooking.mapper;

import com.boje.hotelbooking.models.*;
import com.boje.hotelbooking.dto.*;
import com.boje.hotelbooking.dtoRequest.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    Address toAddress(AddressDTORequest addressDTORequest);
    AddressDTO toAddressDTO(Address address);
    AddressDTORequest toAddressDTORequest(Address address);

    Amenity toAmenity(AmenityDTORequest amenityDTORequest);
    AmenityDTO toAmenityDTO(Amenity amenity);
    AmenityDTORequest toAmenityDTORequest(Amenity amenity);

    Booking toBooking(BookingDTORequest bookingDTORequest);
    BookingDTO toBookingDTO(Booking booking);
    BookingDTORequest toBookingDTORequest(Booking booking);

    ContactInfo toContactInfo(ContactInfoDTORequest contactInfoDTORequest);
    ContactInfoDTO toContactInfoDTO(ContactInfo contactInfo);
    ContactInfoDTORequest toContactInfoDTORequest(ContactInfo contactInfo);

    Hotel toHotel(HotelDTORequest hotelDTORequest);
    HotelDTO toHotelDTO(Hotel hotel);
    HotelDTORequest toHotelDTORequest(Hotel hotel);

    Review toReview(ReviewDTORequest reviewDTORequest);
    ReviewDTO toReviewDTO(Review review);
    ReviewDTORequest toReviewDTORequest(Review review);

    Room toRoom(RoomDTORequest roomDTORequest);
    RoomDTO toRoomDTO(Room room);
    RoomDTORequest toRoomDTORequest(Room room);

    User toUser(UserDTORequest userDTORequest);
    UserDTO toUserDTO(User user);
    UserDTORequest toUserDTORequest(User user);
}