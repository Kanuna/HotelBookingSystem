package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.AmenityDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;
import com.boje.hotelbooking.serviceImp.AmenityServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/amenities")
public class AmenityController {
    private final AmenityServiceImp amenityServiceImp;

    public AmenityController(AmenityServiceImp amenityServiceImp) {
        this.amenityServiceImp = amenityServiceImp;
    }

    @PostMapping("/amenity")
    public ResponseEntity<AmenityDTORequest> createAmenity(@RequestBody AmenityDTORequest amenityDTORequest){
        AmenityDTORequest createdAmenity =  amenityServiceImp.createAmenity(amenityDTORequest);

        URI location = URI.create(String.format("/amenities/%d",createdAmenity.getId()));
        return ResponseEntity.created(location).body(createdAmenity);
    }

    @PutMapping("/{amenityId}")
    public ResponseEntity<AmenityDTO> updateAmenity(@PathVariable int amenityId, AmenityDTO amenityDTO){
        AmenityDTO updatedAmenity = amenityServiceImp.updateAmenity(amenityId,amenityDTO);

        return ResponseEntity.ok(updatedAmenity);
    }

    @DeleteMapping("/{amenityId}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable int amenityId){
        amenityServiceImp.deleteAmenity(amenityId);
        return ResponseEntity.noContent().build();
    }
}