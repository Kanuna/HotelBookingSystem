package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.ContactInfoHotelDTO;
import com.boje.hotelbooking.dtoRequest.ContactInfoHotelDTORequest;
import com.boje.hotelbooking.serviceImp.ContactInfoHotelServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/contact-info-hotels")
public class ContactInfoHotelController {
    private final ContactInfoHotelServiceImp contactInfoHotelServiceImp;

    public ContactInfoHotelController(ContactInfoHotelServiceImp contactInfoHotelServiceImp){
        this.contactInfoHotelServiceImp=contactInfoHotelServiceImp;
    }


    @PostMapping("/contact-info-hotel")
    public ResponseEntity<ContactInfoHotelDTORequest> createContactInfoHotel(@RequestBody ContactInfoHotelDTORequest contactInfoHotelDTORequest){
        ContactInfoHotelDTORequest createdContactInfoHotel = contactInfoHotelServiceImp.createContactInfoHotel(contactInfoHotelDTORequest);

        URI location = URI.create(String.format("contact-info-hotels/%d", createdContactInfoHotel.getId()));
        return ResponseEntity.created(location).body(createdContactInfoHotel);
    }


    @PutMapping("/contact-info-hotel")
    public ResponseEntity<ContactInfoHotelDTO> updateContactInfoHotel(@RequestBody ContactInfoHotelDTORequest contactInfoHotelDTORequest){
        ContactInfoHotelDTO updatedContactInfoHotel = contactInfoHotelServiceImp.updateContactInfoHotel(contactInfoHotelDTORequest);
        return ResponseEntity.ok(updatedContactInfoHotel);
    }


    @DeleteMapping("/{contactInfoHotelId}")
    public ResponseEntity<Void> deleteContactInfoHotel(@PathVariable int contactInfoHotelId){
        contactInfoHotelServiceImp.deleteContactInfoHotel(contactInfoHotelId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{hotelId}/contact-info-hotel")
    public ResponseEntity<ContactInfoHotelDTO> getContactInfoHotelByHotelId(@PathVariable int hotelId){
        ContactInfoHotelDTO contactInfoHotelDTO = contactInfoHotelServiceImp.findByHotelId(hotelId);
        return ResponseEntity.ok(contactInfoHotelDTO);
    }
}