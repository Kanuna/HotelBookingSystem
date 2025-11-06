package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.AmenityDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Amenity;
import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.repositories.AmenityRepository;
import com.boje.hotelbooking.repositories.HotelRepository;
import com.boje.hotelbooking.services.AmenityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityServiceImp implements AmenityService {
    private final AmenityRepository amenityRepository;
    private final HotelRepository  hotelRepository;
    private final EntityMapper entityMapper;

    public AmenityServiceImp(AmenityRepository amenityRepository,
                             HotelRepository hotelRepository,
                             EntityMapper entityMapper) {
        this.amenityRepository = amenityRepository;
        this.hotelRepository = hotelRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public AmenityDTORequest createAmenity(AmenityDTO amenityDTO) {
        Amenity amenity = entityMapper.toAmenity(amenityDTO);
        Amenity savedAmenity = amenityRepository.save(amenity);

        return entityMapper.toAmenityDTORequest(savedAmenity);
    }

    @Override
    public AmenityDTO updateAmenity(AmenityDTORequest amenityDTORequest) {
        Amenity amenity = amenityRepository.findById(amenityDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Amenity not found with id: " + amenityDTORequest.getId()));

        if (amenityDTORequest.getHotel_ids() != null) {
            List<Hotel> hotels = hotelRepository.findAllById(amenityDTORequest.getHotel_ids());
            hotels.forEach(h -> h.getAmenities().add(amenity));
            amenity.setHotels(hotels);
        }

        amenity.setName(amenityDTORequest.getName());

        Amenity savedAmenity = amenityRepository.save(amenity);

        return entityMapper.toAmenityDTO(savedAmenity);
    }

    @Override
    public void deleteAmenity(int amenity_id) {
        if (!amenityRepository.existsById(amenity_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + amenity_id);
        }

        amenityRepository.deleteById(amenity_id);
    }
}