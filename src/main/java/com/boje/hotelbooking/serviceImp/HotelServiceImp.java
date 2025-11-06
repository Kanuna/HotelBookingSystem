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

        AddressDTO addressDTO = entityMapper.toAddressDTO(hotel.getAddress());
        Address address = entityMapper.toAddress(addressDTO);

        hotel.setAddress(address);
        address.setHotel(hotel);

        ContactInfoHotelDTO contactInfoHotelDTO = entityMapper.toContactInfoHotelDTO(hotel.getContactInfoHotel());
        ContactInfoHotel contactInfoHotel = entityMapper.toContactInfoHotel(contactInfoHotelDTO);

        hotel.setContactInfoHotel(contactInfoHotel);
        contactInfoHotel.setHotel(hotel);

        if (hotelDTO.getAmenity_ids() != null && !hotelDTO.getAmenity_ids().isEmpty()) {
            List<Amenity> amenities = amenityRepository.findAllById(hotelDTO.getAmenity_ids());
            hotel.setAmenities(amenities);
        }

        Hotel createdHotel = hotelRepository.save(hotel);

        return entityMapper.toHotelDTORequest(createdHotel);
    }

    @Override
    public HotelDTO updateHotel(HotelDTORequest hotelDTORequest) {
        Hotel hotel = hotelRepository.findById(hotelDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotelDTORequest.getId()));

        ContactInfoHotel contactInfoHotel = contactInfoHotelRepository.findById(hotelDTORequest.getContactInfoHotel_id())
                        .orElseThrow(() -> new ResourceNotFoundException("ContactInfoHotel not found with id: " + hotelDTORequest.getContactInfoHotel_id()));

        Address address = addressRepository.findById(hotelDTORequest.getAddress_id())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + hotelDTORequest.getAddress_id()));


        //Amenities
        if (hotelDTORequest.getAmenity_ids() != null && !hotelDTORequest.getAmenity_ids().isEmpty()) {
            List<Amenity> amenities = amenityRepository.findAllById(hotelDTORequest.getAmenity_ids());
            hotel.setAmenities(amenities);
        }

        //Reviews
        if (hotelDTORequest.getReview_ids() != null) {
            List<Review> reviews = reviewRepository.findAllById(hotelDTORequest.getReview_ids());
            reviews.forEach(r -> r.setHotel(hotel));
            hotel.setReviews(reviews);
        }

        //Rooms
        if (hotelDTORequest.getRoom_ids() != null) {
            List<Room> rooms = roomRepository.findAllById(hotelDTORequest.getRoom_ids());
            rooms.forEach(r -> r.setHotel(hotel));
            hotel.setRooms(rooms);
        }

        //Managers
        if (hotelDTORequest.getManager_ids() != null) {
            List<Manager> managers = managerRepository.findAllById(hotelDTORequest.getManager_ids());
            managers.forEach(m -> m.setHotel(hotel));
            hotel.setManagers(managers);
        }


        hotel.setContactInfoHotel(contactInfoHotel);
        hotel.setName(hotelDTORequest.getName());
        hotel.setDescription(hotelDTORequest.getDescription());
        hotel.setFranchise(hotelDTORequest.getFranchise());
        hotel.setPolicies(hotelDTORequest.getPolicies());
        hotel.setStarRating(hotelDTORequest.getStarRating());
        hotel.setAddress(address);

        Hotel updatedHotel =hotelRepository.save(hotel);

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
