package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.AddressDTO;
import com.boje.hotelbooking.serviceImp.AddressServiceImp;
import com.boje.hotelbooking.dtoRequest.AddressDTORequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressServiceImp addressServiceImp;

    public  AddressController(AddressServiceImp addressServiceImp) {
        this.addressServiceImp = addressServiceImp;
    }

    @PostMapping("/{hotelId}/address")
    public ResponseEntity<AddressDTORequest> createAddress(@PathVariable int hotelId,
                                                           @RequestBody AddressDTORequest addressDTORequest){
        AddressDTORequest createdAddress = addressServiceImp.createAddress(hotelId, addressDTORequest);

        URI location = URI.create(String.format("/addresses/%d",createdAddress.getId()));
        return ResponseEntity.created(location).body(createdAddress);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable int addressId,
                                                           @RequestBody AddressDTO addressDTO){
        AddressDTO updatedAddress = addressServiceImp.updateAddress(addressId, addressDTO);
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