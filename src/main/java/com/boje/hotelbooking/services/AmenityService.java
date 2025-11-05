package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.AmenityDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;

public interface AmenityService {
    AmenityDTORequest createAmenity(AmenityDTO amenityDTO);
    AmenityDTO updateAmenity(AmenityDTORequest amenityDTORequest);
    void deleteAmenity(int amenity_id);
}