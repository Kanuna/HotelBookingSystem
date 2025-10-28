package com.boje.hotelbooking.mapper;

import com.boje.hotelbooking.models.*;
import com.boje.hotelbooking.dto.*;
import com.boje.hotelbooking.dtoRequest.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    Address toAddress(AddressDTO addressDTO);
    AddressDTO toAddressDTO(Address address);
    AddressDTORequest toAddressDTORequest(Address address);

    Amenity toAmenity(AmenityDTO amenityDTO);
    AmenityDTO toAmenityDTO(Amenity amenity);
    AmenityDTORequest toAmenityDTORequest(Amenity amenity);

    Booking toBooking(BookingDTO bookingDTO);
    BookingDTO toBookingDTO(Booking booking);
    BookingDTORequest toBookingDTORequest(Booking booking);

    ContactInfo toContactInfo(ContactInfoDTORequest contactInfoDTORequest);
    ContactInfoDTO toContactInfoDTO(ContactInfo contactInfo);
    ContactInfoDTORequest toContactInfoDTORequest(ContactInfo contactInfo);

    ContactInfoHotel toContactInfoHotel(ContactInfoHotelDTO contactInfoHotelDTO);
    ContactInfoHotelDTO toContactInfoHotelDTO(ContactInfoHotel contactInfoHotel);
    ContactInfoHotelDTORequest toContactInfoHotelDTORequest(ContactInfoHotel contactInfoHotel);

    Hotel toHotel(HotelDTO hotelDTO);
    HotelDTO toHotelDTO(Hotel hotel);
    HotelDTORequest toHotelDTORequest(Hotel hotel);

    Manager toManager(ManagerDTO managerDTO);
    ManagerDTO toManagerDTO(Manager manager);
    ManagerDTORequest toManagerDTORequest(Manager manager);

    Review toReview(ReviewDTO reviewDTO);
    ReviewDTO toReviewDTO(Review review);
    ReviewDTORequest toReviewDTORequest(Review review);

    Room toRoom(RoomDTO roomDTO);
    RoomDTO toRoomDTO(Room room);
    RoomDTORequest toRoomDTORequest(Room room);

    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
    UserDTORequest toUserDTORequest(User user);



    /*default Address toAddress(AddressDTO addressDTO){
        Address address = new Address();
        address.setRegion(addressDTO.getRegion());
        address.setCity(addressDTO.getCity());
        address.setZipCode(addressDTO.getZipCode());
        address.setStreet(addressDTO.getStreet());
        address.setHotel(addressDTO.getHotel());
        return address;
    }
    default AddressDTO toAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setRegion(address.getRegion());
        addressDTO.setCity(address.getCity());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setHotel(address.getHotel());
        return addressDTO;
    }
    default AddressDTORequest toAddressDTORequest(Address address){
        AddressDTORequest addressDTORequest = new AddressDTORequest();
        addressDTORequest.setRegion(address.getRegion());
        addressDTORequest.setCity(address.getCity());
        addressDTORequest.setZipCode(address.getZipCode());
        addressDTORequest.setStreet(address.getStreet());
        addressDTORequest.setHotel(address.getHotel());
        return addressDTORequest;
    }


    default Amenity toAmenity(AmenityDTO amenityDTO){
        Amenity amenity = new Amenity();
        amenity.setName(amenityDTO.getName());
        amenity.setHotels(amenityDTO.getHotels());
        return amenity;
    }
    default AmenityDTO toAmenityDTO(Amenity amenity){
        AmenityDTO amenityDTO = new AmenityDTO();
        amenityDTO.setName(amenity.getName());
        amenityDTO.setHotels(amenity.getHotels());
        return amenityDTO;
    }
    default AmenityDTORequest toAmenityDTORequest(Amenity amenity){
        AmenityDTORequest amenityDTORequest = new AmenityDTORequest();
        amenityDTORequest.setName(amenity.getName());
        amenityDTORequest.setHotels(amenity.getHotels());
        return amenityDTORequest;
    }


    default Booking toBooking(BookingDTO bookingDTO){
        Booking booking = new Booking();
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setCreatedAt(bookingDTO.getCreatedAt());
        booking.setUpdatedAt(bookingDTO.getUpdatedAt());
        booking.setUser(bookingDTO.getUser());
        booking.setRoom(bookingDTO.getRoom());
        return booking;
    }
    default BookingDTO toBookingDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setStartDate(booking.getStartDate());
        bookingDTO.setEndDate(booking.getEndDate());
        bookingDTO.setCreatedAt(booking.getCreatedAt());
        bookingDTO.setUpdatedAt(booking.getUpdatedAt());
        bookingDTO.setUser(booking.getUser());
        bookingDTO.setRoom(booking.getRoom());
        return bookingDTO;
    }

    default BookingDTORequest toBookingDTORequest(Booking booking){
        BookingDTORequest bookingDTORequest = new BookingDTORequest();
        bookingDTORequest.setStartDate(booking.getStartDate());
        bookingDTORequest.setEndDate(booking.getEndDate());
        bookingDTORequest.setCreatedAt(booking.getCreatedAt());
        bookingDTORequest.setUpdatedAt(booking.getUpdatedAt());
        bookingDTORequest.setUser(booking.getUser());
        bookingDTORequest.setRoom(booking.getRoom());
        return bookingDTORequest;
    }

    default ContactInfo toContactInfo(ContactInfoDTORequest contactInfoDTORequest){
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUser(contactInfoDTORequest.getUser());
        contactInfo.setEmail(contactInfoDTORequest.getEmail());
        contactInfo.setPhoneNumber(contactInfoDTORequest.getPhone());
        return contactInfo;
    }
    default ContactInfoDTO toContactInfoDTO(ContactInfo contactInfo){
        ContactInfoDTO contactInfoDTO = new ContactInfoDTORequest();
        contactInfoDTO.setUser(contactInfo.getUser());
        contactInfoDTO.setEmail(contactInfo.getEmail());
        contactInfoDTO.setPhone(contactInfo.getPhoneNumber());
        return contactInfoDTO;
    }
    default ContactInfoDTORequest toContactInfoDTORequest(ContactInfo contactInfo){
        ContactInfoDTORequest contactInfoDTORequest = new ContactInfoDTORequest();
        contactInfoDTORequest.setUser(contactInfo.getUser());
        contactInfoDTORequest.setEmail(contactInfo.getEmail());
        contactInfoDTORequest.setPhone(contactInfo.getPhoneNumber());
        return contactInfoDTORequest;
    }

    default ContactInfoHotel toContactInfoHotel(ContactInfoHotelDTO contactInfoHotelDTO){
        ContactInfoHotel contactInfoHotel = new ContactInfoHotel();
        contactInfoHotel.setHotel(contactInfoHotelDTO.getHotel());
        contactInfoHotel.setManagers(contactInfoHotelDTO.getManagers());
        contactInfoHotel.setHotelEmail(contactInfoHotelDTO.getHotelEmail());
        contactInfoHotel.setHotelPhoneNumber(contactInfoHotelDTO.getHotelPhoneNumber());
        return contactInfoHotel;
    }
    default ContactInfoHotelDTO toContactInfoHotelDTO(ContactInfoHotel contactInfoHotel){
        ContactInfoHotelDTO contactInfoHotelDTO = new ContactInfoHotelDTO();
        contactInfoHotelDTO.setHotel(contactInfoHotel.getHotel());
        contactInfoHotelDTO.setManagers(contactInfoHotel.getManagers());
        contactInfoHotelDTO.setHotelEmail(contactInfoHotel.getHotelEmail());
        contactInfoHotelDTO.setHotelPhoneNumber(contactInfoHotel.getHotelPhoneNumber());
        return  contactInfoHotelDTO;
    }
    default ContactInfoHotelDTORequest toContactInfoHotelDTORequest(ContactInfoHotel contactInfoHotel){
        ContactInfoHotelDTORequest contactInfoHotelDTORequest = new ContactInfoHotelDTORequest();
        contactInfoHotelDTORequest.setHotel(contactInfoHotel.getHotel());
        contactInfoHotelDTORequest.setManagers(contactInfoHotel.getManagers());
        contactInfoHotelDTORequest.setHotelEmail(contactInfoHotel.getHotelEmail());
        contactInfoHotelDTORequest.setHotelPhoneNumber(contactInfoHotel.getHotelPhoneNumber());
        return contactInfoHotelDTORequest;
    }

    default Hotel toHotel(HotelDTO hotelDTO){
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setPolicies(hotelDTO.getPolicies());
        hotel.setReviews(hotelDTO.getReviews());
        hotel.setFranchise(hotelDTO.getFranchise());
        hotel.setAmenities(hotelDTO.getAmenities());
        hotel.setStarRating(hotelDTO.getStarRating());
        hotel.setManagers(hotelDTO.getManagers());
        hotel.setContactInfoHotel(hotelDTO.getContactInfoHotel());
        hotel.setRooms(hotelDTO.getRooms());
        return hotel;
    }
    default HotelDTO toHotelDTO(Hotel hotel){
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setDescription(hotel.getDescription());
        hotelDTO.setPolicies(hotel.getPolicies());
        hotelDTO.setReviews(hotel.getReviews());
        hotelDTO.setFranchise(hotel.getFranchise());
        hotelDTO.setAmenities(hotel.getAmenities());
        hotelDTO.setStarRating(hotel.getStarRating());
        hotelDTO.setManagers(hotel.getManagers());
        hotelDTO.setContactInfoHotel(hotel.getContactInfoHotel());
        hotelDTO.setRooms(hotel.getRooms());
        return hotelDTO;
    }
    default HotelDTORequest toHotelDTORequest(Hotel hotel){
        HotelDTORequest hotelDTORequest = new HotelDTORequest();
        hotelDTORequest.setName(hotel.getName());
        hotelDTORequest.setAddress(hotel.getAddress());
        hotelDTORequest.setDescription(hotel.getDescription());
        hotelDTORequest.setPolicies(hotel.getPolicies());
        hotelDTORequest.setReviews(hotel.getReviews());
        hotelDTORequest.setFranchise(hotel.getFranchise());
        hotelDTORequest.setAmenities(hotel.getAmenities());
        hotelDTORequest.setStarRating(hotel.getStarRating());
        hotelDTORequest.setManagers(hotel.getManagers());
        hotelDTORequest.setContactInfoHotel(hotel.getContactInfoHotel());
        hotelDTORequest.setRooms(hotel.getRooms());
        return hotelDTORequest;
    }

    default Manager toManager(ManagerDTO managerDTO){
        Manager manager = new Manager();
        manager.setFullName(managerDTO.getFullName());
        manager.setEmail(managerDTO.getEmail());
        manager.setPhoneNumber(managerDTO.getPhoneNumber());
        manager.setContactInfoHotel(managerDTO.getContactInfoHotel());
        manager.setHotel(managerDTO.getHotel());
        return manager;
    }
    default ManagerDTO toManagerDTO(Manager manager){
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setFullName(manager.getFullName());
        managerDTO.setEmail(manager.getEmail());
        managerDTO.setPhoneNumber(manager.getPhoneNumber());
        managerDTO.setContactInfoHotel(manager.getContactInfoHotel());
        managerDTO.setHotel(manager.getHotel());
        return managerDTO;
    }
    default ManagerDTORequest toManagerDTORequest(Manager manager){
        ManagerDTORequest managerDTORequest = new ManagerDTORequest();
        managerDTORequest.setFullName(manager.getFullName());
        managerDTORequest.setEmail(manager.getEmail());
        managerDTORequest.setPhoneNumber(manager.getPhoneNumber());
        managerDTORequest.setContactInfoHotel(manager.getContactInfoHotel());
        managerDTORequest.setHotel(manager.getHotel());
        return managerDTORequest;
    }

    default Review toReview(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setUser(reviewDTO.getUser());
        review.setHotel(reviewDTO.getHotel());
        review.setTitle(reviewDTO.getTitle());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setCreatedAt(reviewDTO.getCreatedAt());
        return review;
    }
    default ReviewDTO toReviewDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setUser(review.getUser());
        reviewDTO.setHotel(review.getHotel());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setCreatedAt(review.getCreatedAt());
        return reviewDTO;
    }
    default ReviewDTORequest toReviewDTORequest(Review review){
        ReviewDTORequest reviewDTORequest = new ReviewDTORequest();
        reviewDTORequest.setUser(review.getUser());
        reviewDTORequest.setHotel(review.getHotel());
        reviewDTORequest.setTitle(review.getTitle());
        reviewDTORequest.setComment(review.getComment());
        reviewDTORequest.setRating(review.getRating());
        reviewDTORequest.setCreatedAt(review.getCreatedAt());
        return reviewDTORequest;
    }

    default Room toRoom(RoomDTO roomDTO){
        Room room = new Room();
        room.setHotel(roomDTO.getHotel());
        room.setRoomSize(roomDTO.getRoomSize());
        room.setPrice(roomDTO.getPrice());
        room.setBookings(roomDTO.getBookings());
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setNumberOfBeds(roomDTO.getNumberOfBeds());
        room.setHasKitchen(roomDTO.isHasKitchen());
        room.setOccupied(roomDTO.getOccupied());
        return room;
    }
    default RoomDTO toRoomDTO(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setHotel(room.getHotel());
        roomDTO.setRoomSize(room.getRoomSize());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setBookings(room.getBookings());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        roomDTO.setHasKitchen(room.isHasKitchen());
        roomDTO.setOccupied(room.getOccupied());
        return roomDTO;
    }
    default RoomDTORequest toRoomDTORequest(Room room){
        RoomDTORequest roomDTORequest = new RoomDTORequest();
        roomDTORequest.setHotel(room.getHotel());
        roomDTORequest.setRoomSize(room.getRoomSize());
        roomDTORequest.setPrice(room.getPrice());
        roomDTORequest.setBookings(room.getBookings());
        roomDTORequest.setRoomNumber(room.getRoomNumber());
        roomDTORequest.setNumberOfBeds(room.getNumberOfBeds());
        roomDTORequest.setHasKitchen(room.isHasKitchen());
        roomDTORequest.setOccupied(room.getOccupied());
        return roomDTORequest;
    }

    default User toUser(UserDTO userDTO){
        User user = new User();
        user.setContactInfo(userDTO.getContactInfo());
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());
        user.setFullName(userDTO.getFullName());
        user.setBookings(userDTO.getBookings());
        return user;
    }
    default UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setContactInfo(user.getContactInfo());
        userDTO.setPassword(user.getPassword());
        userDTO.setAge(user.getAge());
        userDTO.setFullName(user.getFullName());
        userDTO.setBookings(user.getBookings());
        return userDTO;
    }
    default UserDTORequest toUserDTORequest(User user){
        UserDTORequest userDTORequest = new UserDTORequest();
        userDTORequest.setContactInfo(user.getContactInfo());
        userDTORequest.setPassword(user.getPassword());
        userDTORequest.setAge(user.getAge());
        userDTORequest.setFullName(user.getFullName());
        userDTORequest.setBookings(user.getBookings());
        return userDTORequest;
    }*/
}