package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.AddressDTO;
import com.boje.hotelbooking.serviceImp.AddressServiceImp;
import com.boje.hotelbooking.dtoRequest.AddressDTORequest;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@PermitAll
@EnableMethodSecurity
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressServiceImp addressServiceImp;

    public  AddressController(AddressServiceImp addressServiceImp) {
        this.addressServiceImp = addressServiceImp;
    }

    @PostMapping("/{hotelId}/address")
    public ResponseEntity<AddressDTORequest> createAddress(@PathVariable int hotelId,
                                                           @RequestBody AddressDTO addressDTO){
        AddressDTORequest createdAddress = addressServiceImp.createAddress(hotelId, addressDTO);

        URI location = URI.create(String.format("/addresses/%d",createdAddress.getId()));
        return ResponseEntity.created(location).body(createdAddress);
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTORequest addressDTORequest){
        AddressDTO updatedAddress = addressServiceImp.updateAddress(addressDTORequest);
        return  ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/hotel/{addressId}/address")
    public ResponseEntity<Void> deleteAddress(@PathVariable int addressId){
        addressServiceImp.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hotels/{hotelId}/address")
    public ResponseEntity<AddressDTO> getAddressByHotelId(@PathVariable int hotelId){
        AddressDTO addressDTO = addressServiceImp.findAddressByHotelId(hotelId);
        return ResponseEntity.ok(addressDTO);
    }
}