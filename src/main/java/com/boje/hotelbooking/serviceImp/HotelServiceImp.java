package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.AddressDTO;
import com.boje.hotelbooking.dto.ContactInfoHotelDTO;
import com.boje.hotelbooking.dto.HotelDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;
import com.boje.hotelbooking.dtoRequest.HotelDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.*;
import com.boje.hotelbooking.repositories.*;
import com.boje.hotelbooking.services.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImp implements HotelService {
    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final ContactInfoHotelRepository contactInfoHotelRepository;
    private final ReviewRepository reviewRepository;
    private final RoomRepository roomRepository;
    private final AddressRepository addressRepository;
    private final ManagerRepository  managerRepository;
    private final EntityMapper entityMapper;

    public HotelServiceImp(HotelRepository hotelRepository,
                           AmenityRepository amenityRepository,
                           ContactInfoHotelRepository contactInfoHotelRepository,
                           ReviewRepository reviewRepository,
                           RoomRepository roomRepository,
                           AddressRepository addressRepository,
                           ManagerRepository  managerRepository,
                           EntityMapper entityMapper) {
        this.hotelRepository = hotelRepository;
        this.amenityRepository = amenityRepository;
        this.contactInfoHotelRepository = contactInfoHotelRepository;
        this.reviewRepository = reviewRepository;
        this.roomRepository = roomRepository;
        this.addressRepository = addressRepository;
        this.managerRepository = managerRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public HotelDTORequest createHotel(HotelDTO hotelDTO) {
        Hotel hotel = entityMapper.toHotel(hotelDTO);

        Address address = new Address();
        address.setCity(hotelDTO.getAddress().getCity());
        address.setRegion(hotelDTO.getAddress().getRegion());
        address.setStreet(hotelDTO.getAddress().getStreet());
        address.setZipCode(hotelDTO.getAddress().getZipCode());

        address.setHotel(hotel);
        hotel.setAddress(address);

        ContactInfoHotel contactInfoHotel = new ContactInfoHotel();
        contactInfoHotel.setHotelEmail(hotelDTO.getContactInfoHotel().getHotelEmail());
        contactInfoHotel.setHotelPhoneNumber(hotelDTO.getContactInfoHotel().getHotelPhoneNumber());

        contactInfoHotel.setHotel(hotel);
        hotel.setContactInfoHotel(contactInfoHotel);

        if (hotelDTO.getAmenity_ids() != null && !hotelDTO.getAmenity_ids().isEmpty()) {
            List<Amenity> amenities = amenityRepository.findAllById(hotelDTO.getAmenity_ids());
            hotel.setAmenities(amenities);
        }

        Hotel createdHotel = hotelRepository.save(hotel);

        return entityMapper.toHotelDTORequest(createdHotel);
    }

    @Override
    public HotelDTO updateHotel(int hotel_id, HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotel_id));


        //Amenities
        if (hotelDTO.getAmenity_ids() != null && !hotelDTO.getAmenity_ids().isEmpty()) {
            List<Amenity> amenities = amenityRepository.findAllById(hotelDTO.getAmenity_ids());
            hotel.getAmenities().clear();
            hotel.getAmenities().addAll(amenities);
        }

        //Reviews
        if (hotelDTO.getReview_ids() != null) {
            List<Review> reviews = reviewRepository.findAllById(hotelDTO.getReview_ids());
            reviews.forEach(r -> r.setHotel(hotel));
            hotel.getReviews().clear();
            hotel.getReviews().addAll(reviews);
        }

        //Rooms
        if (hotelDTO.getRoom_ids() != null) {
            List<Room> rooms = roomRepository.findAllById(hotelDTO.getRoom_ids());
            rooms.forEach(r -> r.setHotel(hotel));
            hotel.getRooms().clear();
            hotel.getRooms().addAll(rooms);
        }

        //ContactInfo
        if (hotelDTO.getContactInfoHotel() != null) {
            ContactInfoHotel contactInfoHotel = entityMapper.toContactInfoHotel(hotelDTO.getContactInfoHotel());
            contactInfoHotel.setHotel(hotel);
            hotel.setContactInfoHotel(contactInfoHotel);
        }

        hotel.setName(hotelDTO.getName());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setFranchise(hotelDTO.getFranchise());
        hotel.setPolicies(hotelDTO.getPolicies());
        hotel.setStarRating(hotelDTO.getStarRating());

        //Address
        if (hotelDTO.getAddress() != null) {
            Address address = entityMapper.toAddress(hotelDTO.getAddress());
            address.setHotel(hotel);
            hotel.setAddress(address);
        }

        Hotel updatedHotel = hotelRepository.save(hotel);

        return  entityMapper.toHotelDTO(updatedHotel);
    }

    @Override
    public void deleteHotel(int hotel_id) {
        if (!hotelRepository.existsById(hotel_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + hotel_id);
        }

        hotelRepository.deleteById(hotel_id);
    }

    @Override
    public HotelDTO findByName(String hotelName) {
        Hotel hotel = hotelRepository.findByName(hotelName)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with name: " + hotelName));

        return entityMapper.toHotelDTORequest(hotel);
    }

    @Override
    public List<HotelDTO> findByAddressZipCode(short zipCode) {
        List<Hotel> hotels = hotelRepository.findByAddress_ZipCode(zipCode)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with zipcode: " + zipCode));

        return hotels.stream()
                .map(entityMapper::toHotelDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<HotelDTO> findByAddressCity(String city) {
        List<Hotel> hotels = hotelRepository.findByAddress_City(city)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with city: " + city));

        return  hotels.stream()
                .map(entityMapper::toHotelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> findByAddressRegion(String region) {
        List<Hotel> hotels = hotelRepository.findByAddress_Region(region)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with region: " + region));

        return hotels.stream()
                .map(entityMapper::toHotelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> findByFranchise(String franchise) {
        List<Hotel> hotels = hotelRepository.findByFranchise(franchise)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with franchise: " + franchise));

        return hotels.stream()
                .map(entityMapper::toHotelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> findByAmenities(List<AmenityDTORequest> amenityDTORequests) {
        List<Amenity> amenities = amenityDTORequests.stream()
                .map(amenity -> amenityRepository.findById(amenity.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Amenity not found: " + amenity.getName())))
                .collect(Collectors.toList());

        List<Hotel> hotels = hotelRepository.findByAmenities(amenities)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hotels not found with any of the chosen amenities."));

        return hotels.stream()
                .map(entityMapper::toHotelDTO)
                .collect(Collectors.toList());
    }
}
