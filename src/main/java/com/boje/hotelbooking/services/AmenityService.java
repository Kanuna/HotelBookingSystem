package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.AmenityDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;

public interface AmenityService {
    AmenityDTORequest createAmenity(AmenityDTORequest amenityDTORequest);
    AmenityDTORequest updateAmenity(AmenityDTORequest amenityDTORequest);
    boolean deleteAmenity(int amenity_id);
}