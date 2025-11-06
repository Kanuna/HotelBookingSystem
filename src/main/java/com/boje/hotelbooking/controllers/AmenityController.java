package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.AmenityDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;
import com.boje.hotelbooking.serviceImp.AmenityServiceImp;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@PermitAll
@EnableMethodSecurity
@RestController
@RequestMapping("/amenities")
public class AmenityController {
    private final AmenityServiceImp amenityServiceImp;

    public AmenityController(AmenityServiceImp amenityServiceImp) {
        this.amenityServiceImp = amenityServiceImp;
    }

    @PostMapping("/amenity")
    public ResponseEntity<AmenityDTORequest> createAmenity(@RequestBody AmenityDTO amenityDTO){
        AmenityDTORequest createdAmenity =  amenityServiceImp.createAmenity(amenityDTO);

        URI location = URI.create(String.format("/amenities/%d",createdAmenity.getId()));
        return ResponseEntity.created(location).body(createdAmenity);
    }

    @PutMapping("/amenity")
    public ResponseEntity<AmenityDTO> updateAmenity(@RequestBody AmenityDTORequest amenityDTORequest){
        AmenityDTO updatedAmenity = amenityServiceImp.updateAmenity(amenityDTORequest);

        return ResponseEntity.ok(updatedAmenity);
    }

    @DeleteMapping("/{amenityId}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable int amenityId){
        amenityServiceImp.deleteAmenity(amenityId);
        return ResponseEntity.noContent().build();
    }
}