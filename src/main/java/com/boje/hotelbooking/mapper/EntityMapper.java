package com.boje.hotelbooking.mapper;

import com.boje.hotelbooking.models.*;
import com.boje.hotelbooking.dto.*;
import com.boje.hotelbooking.dtoRequest.*;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Named("ToAddress")
    Address toAddress(AddressDTO addressDTO);
    @Named("ToAddressDTO")
    AddressDTO toAddressDTO(Address address);
    @Named("toAddressDTORequest")
    AddressDTORequest toAddressDTORequest(Address address);

    @Named("toAmenity")
    Amenity toAmenity(AmenityDTO amenityDTO);
    @Named("toAmenityDTO")
    AmenityDTO toAmenityDTO(Amenity amenity);
    @Named("toAmenityDTORequest")
    AmenityDTORequest toAmenityDTORequest(Amenity amenity);

    List<Amenity> toAmenityList(List<AmenityDTO> dtos);
    List<AmenityDTO> toAmenityDTOList(List<Amenity> entities);


    @Named("toBooking")
    Booking toBooking(BookingDTO bookingDTO);
    @Named("toBookingDTO")
    BookingDTO toBookingDTO(Booking booking);
    @Named("toBookingDTORequest")
    BookingDTORequest toBookingDTORequest(Booking booking);


    List<Booking> toBookingList(List<BookingDTO> dtos);
    List<BookingDTO> toBookingDTOList(List<Booking> entites);


    @Named("toContactInfo")
    ContactInfo toContactInfo(ContactInfoDTO contactInfoDTO);
    @Named("toContactInfoDTO")
    ContactInfoDTO toContactInfoDTO(ContactInfo contactInfo);
    @Named("toContactInfoDTORequest")
    ContactInfoDTORequest toContactInfoDTORequest(ContactInfo contactInfo);

    @Named("toContactInfoHotel")
    ContactInfoHotel toContactInfoHotel(ContactInfoHotelDTO contactInfoHotelDTO);
    @Named("toContactInfoHotelDTO")
    ContactInfoHotelDTO toContactInfoHotelDTO(ContactInfoHotel contactInfoHotel);
    @Named("toContactInfoHotelDTORequest")
    ContactInfoHotelDTORequest toContactInfoHotelDTORequest(ContactInfoHotel contactInfoHotel);


    @Named("toHotel")
    Hotel toHotel(HotelDTO hotelDTO);
    @Named("toHotelDTO")
    HotelDTO toHotelDTO(Hotel hotel);
    @Named("toHotelDTORequest")
    HotelDTORequest toHotelDTORequest(Hotel hotel);


    List<Hotel> toHotelList(List<HotelDTO> dtos);
    List<HotelDTO> toHotelDTOList(List<Hotel> entities);


    @Named("toManager")
    Manager toManager(ManagerDTO managerDTO);
    @Named("toManagerDTO")
    ManagerDTO toManagerDTO(Manager manager);
    @Named("toManagerDTORequest")
    ManagerDTORequest toManagerDTORequest(Manager manager);


    List<Manager> toManagerList(List<ManagerDTO> dtos);
    List<ManagerDTO> toManagerDTOList(List<Manager> entities);


    @Named("Reviews")
    Review toReview(ReviewDTO reviewDTO);
    @Named("toReviewDTO")
    ReviewDTO toReviewDTO(Review review);
    @Named("toReviewDTORequest")
    ReviewDTORequest toReviewDTORequest(Review review);

    List<Review> toReviewList(List<ReviewDTO> dtos);
    List<ReviewDTO> toReviewDTOList(List<Review> entities);


    @Named("toRoom")
    Room toRoom(RoomDTO roomDTO);
    @Named("toRoomDTO")
    RoomDTO toRoomDTO(Room room);
    @Name("toRoomDTORequest")
    RoomDTORequest toRoomDTORequest(Room room);

    List<Room> toRoomList(List<RoomDTO> dtos);
    List<RoomDTO> toRoomDTOList(List<Room> entities);


    @Named("toUser")
    User toUser(UserDTO userDTO);
    @Named("toUSerDTO")
    UserDTO toUserDTO(User user);
    @Named("toUserDTORequest")
    UserDTORequest toUserDTORequest(User user);


    /*default Address toAddress(AddressDTO addressDTO){
        Address address = new Address();
        address.setRegion(addressDTO.getRegion());
        address.setCity(addressDTO.getCity());
        address.setZipCode(addressDTO.getZipCode());
        address.setStreet(addressDTO.getStreet());
        address.setHotel(toHotel(addressDTO.getHotel()));
        return address;
    }
    default AddressDTO toAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setRegion(address.getRegion());
        addressDTO.setCity(address.getCity());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setHotel(toHotelDTO(address.getHotel()));
        return addressDTO;
    }
    default AddressDTORequest toAddressDTORequest(Address address){
        AddressDTORequest addressDTORequest = new AddressDTORequest();
        addressDTORequest.setRegion(address.getRegion());
        addressDTORequest.setCity(address.getCity());
        addressDTORequest.setZipCode(address.getZipCode());
        addressDTORequest.setStreet(address.getStreet());
        addressDTORequest.setHotel(toHotelDTO(address.getHotel()));
        return addressDTORequest;
    }


    default Amenity toAmenity(AmenityDTO amenityDTO){
        Amenity amenity = new Amenity();
        amenity.setName(amenityDTO.getName());
        amenity.setHotels(toHotelList(amenityDTO.getHotels()));
        return amenity;
    }
    default AmenityDTO toAmenityDTO(Amenity amenity){
        AmenityDTO amenityDTO = new AmenityDTO();
        amenityDTO.setName(amenity.getName());
        amenityDTO.setHotels(toHotelDTOList(amenity.getHotels()));
        return amenityDTO;
    }
    default AmenityDTORequest toAmenityDTORequest(Amenity amenity){
        AmenityDTORequest amenityDTORequest = new AmenityDTORequest();
        amenityDTORequest.setName(amenity.getName());
        amenityDTORequest.setHotels(toHotelDTOList(amenity.getHotels()));
        return amenityDTORequest;
    }


    default Booking toBooking(BookingDTO bookingDTO){
        Booking booking = new Booking();
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setCreatedAt(bookingDTO.getCreatedAt());
        booking.setUpdatedAt(bookingDTO.getUpdatedAt());
        booking.setUser(toUser(bookingDTO.getUser()));
        booking.setRoom(toRoom(bookingDTO.getRoom()));
        return booking;
    }
    default BookingDTO toBookingDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setStartDate(booking.getStartDate());
        bookingDTO.setEndDate(booking.getEndDate());
        bookingDTO.setCreatedAt(booking.getCreatedAt());
        bookingDTO.setUpdatedAt(booking.getUpdatedAt());
        bookingDTO.setUser(toUserDTO(booking.getUser()));
        bookingDTO.setRoom(toRoomDTO(booking.getRoom()));
        return bookingDTO;
    }

    default BookingDTORequest toBookingDTORequest(Booking booking){
        BookingDTORequest bookingDTORequest = new BookingDTORequest();
        bookingDTORequest.setStartDate(booking.getStartDate());
        bookingDTORequest.setEndDate(booking.getEndDate());
        bookingDTORequest.setCreatedAt(booking.getCreatedAt());
        bookingDTORequest.setUpdatedAt(booking.getUpdatedAt());
        bookingDTORequest.setUser(toUserDTO(booking.getUser()));
        bookingDTORequest.setRoom(toRoomDTO(booking.getRoom()));
        return bookingDTORequest;
    }

    default ContactInfo toContactInfo(ContactInfoDTO contactInfoDTO){
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUser(toUser(contactInfoDTO.getUser()));
        contactInfo.setEmail(contactInfoDTO.getEmail());
        contactInfo.setPhoneNumber(contactInfoDTO.getPhone());
        return contactInfo;
    }
    default ContactInfoDTO toContactInfoDTO(ContactInfo contactInfo){
        ContactInfoDTO contactInfoDTO = new ContactInfoDTORequest();
        contactInfoDTO.setUser(toUserDTO(contactInfo.getUser()));
        contactInfoDTO.setEmail(contactInfo.getEmail());
        contactInfoDTO.setPhone(contactInfo.getPhoneNumber());
        return contactInfoDTO;
    }
    default ContactInfoDTORequest toContactInfoDTORequest(ContactInfo contactInfo){
        ContactInfoDTORequest contactInfoDTORequest = new ContactInfoDTORequest();
        contactInfoDTORequest.setUser(toUserDTO(contactInfo.getUser()));
        contactInfoDTORequest.setEmail(contactInfo.getEmail());
        contactInfoDTORequest.setPhone(contactInfo.getPhoneNumber());
        return contactInfoDTORequest;
    }

    default ContactInfoHotel toContactInfoHotel(ContactInfoHotelDTO contactInfoHotelDTO){
        ContactInfoHotel contactInfoHotel = new ContactInfoHotel();
        contactInfoHotel.setHotel(toHotel(contactInfoHotelDTO.getHotel()));
        contactInfoHotel.setManagers(toManagerList(contactInfoHotelDTO.getManagers()));
        contactInfoHotel.setHotelEmail(contactInfoHotelDTO.getHotelEmail());
        contactInfoHotel.setHotelPhoneNumber(contactInfoHotelDTO.getHotelPhoneNumber());
        return contactInfoHotel;
    }
    default ContactInfoHotelDTO toContactInfoHotelDTO(ContactInfoHotel contactInfoHotel){
        ContactInfoHotelDTO contactInfoHotelDTO = new ContactInfoHotelDTO();
        contactInfoHotelDTO.setHotel(toHotelDTORequest(contactInfoHotel.getHotel()));
        contactInfoHotelDTO.setManagers(toManagerDTOList(contactInfoHotel.getManagers()));
        contactInfoHotelDTO.setHotelEmail(contactInfoHotel.getHotelEmail());
        contactInfoHotelDTO.setHotelPhoneNumber(contactInfoHotel.getHotelPhoneNumber());
        return  contactInfoHotelDTO;
    }
    default ContactInfoHotelDTORequest toContactInfoHotelDTORequest(ContactInfoHotel contactInfoHotel){
        ContactInfoHotelDTORequest contactInfoHotelDTORequest = new ContactInfoHotelDTORequest();
        contactInfoHotelDTORequest.setHotel(toHotelDTORequest(contactInfoHotel.getHotel()));
        contactInfoHotelDTORequest.setManagers(toManagerDTOList(contactInfoHotel.getManagers()));
        contactInfoHotelDTORequest.setHotelEmail(contactInfoHotel.getHotelEmail());
        contactInfoHotelDTORequest.setHotelPhoneNumber(contactInfoHotel.getHotelPhoneNumber());
        return contactInfoHotelDTORequest;
    }

    default Hotel toHotel(HotelDTO hotelDTO){
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(toAddress(hotelDTO.getAddress()));
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setPolicies(hotelDTO.getPolicies());
        hotel.setReviews(toReviewList(hotelDTO.getReviews()));
        hotel.setFranchise(hotelDTO.getFranchise());
        hotel.setAmenities(toAmenityList(hotelDTO.getAmenities()));
        hotel.setStarRating(hotelDTO.getStarRating());
        hotel.setManagers(toManagerList(hotelDTO.getManagers()));
        hotel.setContactInfoHotel(toContactInfoHotel(hotelDTO.getContactInfoHotel()));
        hotel.setRooms(toRoomList(hotelDTO.getRooms()));
        return hotel;
    }
    default HotelDTO toHotelDTO(Hotel hotel){
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(toAddressDTO(hotel.getAddress()));
        hotelDTO.setDescription(hotel.getDescription());
        hotelDTO.setPolicies(hotel.getPolicies());
        hotelDTO.setReviews(toReviewDTOList(hotel.getReviews()));
        hotelDTO.setFranchise(hotel.getFranchise());
        hotelDTO.setAmenities(toAmenityDTOList(hotel.getAmenities()));
        hotelDTO.setStarRating(hotel.getStarRating());
        hotelDTO.setManagers(toManagerDTOList(hotel.getManagers()));
        hotelDTO.setContactInfoHotel(toContactInfoHotelDTO(hotel.getContactInfoHotel()));
        hotelDTO.setRooms(toRoomDTOList(hotel.getRooms()));
        return hotelDTO;
    }
    default HotelDTORequest toHotelDTORequest(Hotel hotel){
        HotelDTORequest hotelDTORequest = new HotelDTORequest();
        hotelDTORequest.setName(hotel.getName());
        hotelDTORequest.setAddress(toAddressDTO(hotel.getAddress()));
        hotelDTORequest.setDescription(hotel.getDescription());
        hotelDTORequest.setPolicies(hotel.getPolicies());
        hotelDTORequest.setReviews(toReviewDTOList(hotel.getReviews()));
        hotelDTORequest.setFranchise(hotel.getFranchise());
        hotelDTORequest.setAmenities(toAmenityDTOList(hotel.getAmenities()));
        hotelDTORequest.setStarRating(hotel.getStarRating());
        hotelDTORequest.setManagers(toManagerDTOList(hotel.getManagers()));
        hotelDTORequest.setContactInfoHotel(toContactInfoHotelDTO(hotel.getContactInfoHotel()));
        hotelDTORequest.setRooms(toRoomDTOList(hotel.getRooms()));
        return hotelDTORequest;
    }

    default Manager toManager(ManagerDTO managerDTO){
        Manager manager = new Manager();
        manager.setFullName(managerDTO.getFullName());
        manager.setEmail(managerDTO.getEmail());
        manager.setPhoneNumber(managerDTO.getPhoneNumber());
        manager.setContactInfoHotel(toContactInfoHotel(managerDTO.getContactInfoHotel()));
        manager.setHotel(toHotel(managerDTO.getHotel()));
        return manager;
    }
    default ManagerDTO toManagerDTO(Manager manager){
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setFullName(manager.getFullName());
        managerDTO.setEmail(manager.getEmail());
        managerDTO.setPhoneNumber(manager.getPhoneNumber());
        managerDTO.setContactInfoHotel(toContactInfoHotelDTO(manager.getContactInfoHotel()));
        managerDTO.setHotel(toHotelDTORequest(manager.getHotel()));
        return managerDTO;
    }
    default ManagerDTORequest toManagerDTORequest(Manager manager){
        ManagerDTORequest managerDTORequest = new ManagerDTORequest();
        managerDTORequest.setFullName(manager.getFullName());
        managerDTORequest.setEmail(manager.getEmail());
        managerDTORequest.setPhoneNumber(manager.getPhoneNumber());
        managerDTORequest.setContactInfoHotel(toContactInfoHotelDTO(manager.getContactInfoHotel()));
        managerDTORequest.setHotel(toHotelDTORequest(manager.getHotel()));
        return managerDTORequest;
    }

    default Review toReview(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setUser(toUser(reviewDTO.getUser()));
        review.setHotel(toHotel(reviewDTO.getHotel()));
        review.setTitle(reviewDTO.getTitle());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setCreatedAt(reviewDTO.getCreatedAt());
        return review;
    }
    default ReviewDTO toReviewDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setUser(toUserDTO(review.getUser()));
        reviewDTO.setHotel(toHotelDTORequest(review.getHotel()));
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setCreatedAt(review.getCreatedAt());
        return reviewDTO;
    }
    default ReviewDTORequest toReviewDTORequest(Review review){
        ReviewDTORequest reviewDTORequest = new ReviewDTORequest();
        reviewDTORequest.setUser(toUserDTO(review.getUser()));
        reviewDTORequest.setHotel(toHotelDTORequest(review.getHotel()));
        reviewDTORequest.setTitle(review.getTitle());
        reviewDTORequest.setComment(review.getComment());
        reviewDTORequest.setRating(review.getRating());
        reviewDTORequest.setCreatedAt(review.getCreatedAt());
        return reviewDTORequest;
    }

    default Room toRoom(RoomDTO roomDTO){
        Room room = new Room();
        room.setHotel(toHotel(roomDTO.getHotel()));
        room.setRoomSize(roomDTO.getRoomSize());
        room.setPrice(roomDTO.getPrice());
        room.setBookings(toBookingList(roomDTO.getBookings()));
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setNumberOfBeds(roomDTO.getNumberOfBeds());
        room.setHasKitchen(roomDTO.isHasKitchen());
        room.setOccupied(roomDTO.getOccupied());
        return room;
    }
    default RoomDTO toRoomDTO(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setHotel(toHotelDTO(room.getHotel()));
        roomDTO.setRoomSize(room.getRoomSize());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setBookings(toBookingDTOList(room.getBookings()));
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        roomDTO.setHasKitchen(room.isHasKitchen());
        roomDTO.setOccupied(room.getOccupied());
        return roomDTO;
    }
    default RoomDTORequest toRoomDTORequest(Room room){
        RoomDTORequest roomDTORequest = new RoomDTORequest();
        roomDTORequest.setHotel(toHotelDTORequest(room.getHotel()));
        roomDTORequest.setRoomSize(room.getRoomSize());
        roomDTORequest.setPrice(room.getPrice());
        roomDTORequest.setBookings(toBookingDTOList(room.getBookings()));
        roomDTORequest.setRoomNumber(room.getRoomNumber());
        roomDTORequest.setNumberOfBeds(room.getNumberOfBeds());
        roomDTORequest.setHasKitchen(room.isHasKitchen());
        roomDTORequest.setOccupied(room.getOccupied());
        return roomDTORequest;
    }

    default User toUser(UserDTO userDTO){
        User user = new User();
        user.setContactInfo(toContactInfo(userDTO.getContactInfo()));
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());
        user.setFullName(userDTO.getFullName());
        user.setBookings(toBookingList(userDTO.getBookings()));
        return user;
    }
    default UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setContactInfo(toContactInfoDTO(user.getContactInfo()));
        userDTO.setPassword(user.getPassword());
        userDTO.setAge(user.getAge());
        userDTO.setFullName(user.getFullName());
        userDTO.setBookings(toBookingDTOList(user.getBookings()));
        return userDTO;
    }
    default UserDTORequest toUserDTORequest(User user){
        UserDTORequest userDTORequest = new UserDTORequest();
        userDTORequest.setContactInfo(toContactInfoDTO(user.getContactInfo()));
        userDTORequest.setPassword(user.getPassword());
        userDTORequest.setAge(user.getAge());
        userDTORequest.setFullName(user.getFullName());
        userDTORequest.setBookings(toBookingDTOList(user.getBookings()));
        return userDTORequest;
    }
*/}