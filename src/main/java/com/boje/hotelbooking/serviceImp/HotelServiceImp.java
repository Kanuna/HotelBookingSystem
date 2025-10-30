package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.AddressDTO;
import com.boje.hotelbooking.dto.ContactInfoHotelDTO;
import com.boje.hotelbooking.dto.HotelDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;
import com.boje.hotelbooking.dtoRequest.HotelDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Address;
import com.boje.hotelbooking.models.Amenity;
import com.boje.hotelbooking.models.ContactInfoHotel;
import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.repositories.AmenityRepository;
import com.boje.hotelbooking.repositories.HotelRepository;
import com.boje.hotelbooking.services.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImp implements HotelService {
    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final EntityMapper entityMapper;

    public HotelServiceImp(HotelRepository hotelRepository,
                           AmenityRepository amenityRepository,
                           EntityMapper entityMapper) {
        this.hotelRepository = hotelRepository;
        this.amenityRepository = amenityRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public HotelDTORequest createHotel(HotelDTORequest hotelDTORequest) {
        Hotel hotel = entityMapper.toHotel(hotelDTORequest);

        AddressDTO addressDTO = entityMapper.toAddressDTO(hotelDTORequest.getAddress());
        Address address = entityMapper.toAddress(addressDTO);

        hotel.setAddress(address);
        address.setHotel(hotel);

        ContactInfoHotelDTO contactInfoHotelDTO = entityMapper.toContactInfoHotelDTO(hotelDTORequest.getContactInfoHotel());
        ContactInfoHotel contactInfoHotel = entityMapper.toContactInfoHotel(contactInfoHotelDTO);

        hotel.setContactInfoHotel(contactInfoHotel);
        contactInfoHotel.setHotel(hotel);

        Hotel createdHotel = hotelRepository.save(hotel);

        return entityMapper.toHotelDTORequest(createdHotel);
    }

    @Override
    public HotelDTORequest updateHotel(int hotel_id, HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotel_id));

        hotel.setContactInfoHotel(hotelDTO.getContactInfoHotel());
        hotel.setAmenities(hotelDTO.getAmenities());
        hotel.setName(hotelDTO.getName());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setFranchise(hotelDTO.getFranchise());
        hotel.setPolicies(hotelDTO.getPolicies());
        hotel.setReviews(hotelDTO.getReviews());
        hotel.setRooms(hotelDTO.getRooms());
        hotel.setStarRating(hotelDTO.getStarRating());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setManagers(hotelDTO.getManagers());

        Hotel updatedHotel =hotelRepository.save(hotel);

        return  entityMapper.toHotelDTORequest(updatedHotel);
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
