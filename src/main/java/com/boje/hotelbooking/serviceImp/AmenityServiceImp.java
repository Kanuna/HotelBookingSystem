package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.AmenityDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Amenity;
import com.boje.hotelbooking.repositories.AmenityRepository;
import com.boje.hotelbooking.services.AmenityService;
import org.springframework.stereotype.Service;

@Service
public class AmenityServiceImp implements AmenityService {
    private final AmenityRepository amenityRepository;
    private final EntityMapper entityMapper;

    public AmenityServiceImp(AmenityRepository amenityRepository, EntityMapper entityMapper) {
        this.amenityRepository = amenityRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public AmenityDTORequest createAmenity(AmenityDTORequest amenityDTORequest) {
        Amenity amenity = entityMapper.toAmenity(amenityDTORequest);
        Amenity savedAmenity = amenityRepository.save(amenity);

        return entityMapper.toAmenityDTORequest(savedAmenity);
    }

    @Override
    public AmenityDTORequest updateAmenity(int amenity_id, AmenityDTO amenityDTO) {
        Amenity amenity = amenityRepository.findById(amenity_id)
                .orElseThrow(() -> new ResourceNotFoundException("Amenity not found with id: " + amenity_id));

        amenity.setHotels(amenityDTO.getHotels());
        amenity.setName(amenityDTO.getName());

        Amenity savedAmenity = amenityRepository.save(amenity);

        return entityMapper.toAmenityDTORequest(savedAmenity);
    }

    @Override
    public void deleteAmenity(int amenity_id) {
        if (!amenityRepository.existsById(amenity_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + amenity_id);
        }

        amenityRepository.deleteById(amenity_id);
    }
}