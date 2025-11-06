package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.HotelDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;
import com.boje.hotelbooking.dtoRequest.HotelDTORequest;
import com.boje.hotelbooking.serviceImp.HotelServiceImp;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@PermitAll
@EnableMethodSecurity
@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelServiceImp hotelServiceImp;

    public HotelController(HotelServiceImp hotelServiceImp) {
        this.hotelServiceImp = hotelServiceImp;
    }


    @PostMapping("/hotel")
    public ResponseEntity<HotelDTORequest> createHotel(@RequestBody HotelDTO hotelDTO){
        HotelDTORequest createdHotel =  hotelServiceImp.createHotel(hotelDTO);

        URI location = URI.create(String.format("/hotels%d", createdHotel.getId()));
        return ResponseEntity.created(location).body(createdHotel);
    }

    @PutMapping("/{hotel_id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable int hotel_id,@RequestBody HotelDTORequest hotelDTORequest){
        HotelDTO updatedHotel  = hotelServiceImp.updateHotel(hotel_id, hotelDTORequest);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable int hotelId){
        hotelServiceImp.deleteHotel(hotelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<HotelDTO> getHotelByName(@RequestParam String hotelName){
        HotelDTO hotel = hotelServiceImp.findByName(hotelName);
        return hotel != null
                ? ResponseEntity.ok(hotel)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-zipcode")
    public ResponseEntity<List<HotelDTO>> getHotelsByZipcode(@RequestParam short zipCode){
        List<HotelDTO> hotels = hotelServiceImp.findByAddressZipCode(zipCode);
        return hotels.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(hotels);
    }

    @GetMapping("/by-city")
    public ResponseEntity<List<HotelDTO>> getHotelsByCity(@RequestParam String city){
        List<HotelDTO> hotels = hotelServiceImp.findByAddressCity(city);
        return hotels.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(hotels);
    }

    @GetMapping("/by-region")
    public ResponseEntity<List<HotelDTO>> getHotelsByRegion(@RequestParam String region){
        List<HotelDTO> hotels = hotelServiceImp.findByAddressRegion(region);
        return hotels.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(hotels);
    }


    @GetMapping("/by-franchise")
    public ResponseEntity<List<HotelDTO>> getHotelsByFranchise(@RequestParam String franchise){
        List<HotelDTO> hotels = hotelServiceImp.findByFranchise(franchise);
        return hotels.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(hotels);
    }


    @GetMapping("/by-amenities")
    public ResponseEntity<List<HotelDTO>> getHotelsByAmenities(@RequestParam List<AmenityDTORequest> amenities){
        List<HotelDTO> hotels = hotelServiceImp.findByAmenities(amenities);
        return hotels.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(hotels);
    }
}