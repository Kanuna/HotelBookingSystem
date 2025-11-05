package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.ContactInfoDTO;
import com.boje.hotelbooking.dtoRequest.ContactInfoDTORequest;
import com.boje.hotelbooking.serviceImp.ContactInfoServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/contact-infos")
public class ContactInfoController {
    private final ContactInfoServiceImp contactInfoServiceImp;

    public ContactInfoController(ContactInfoServiceImp contactInfoServiceImp) {
        this.contactInfoServiceImp = contactInfoServiceImp;
    }


    @PostMapping("/contact-info")
    public ResponseEntity<ContactInfoDTORequest> createContactInfo(@RequestBody ContactInfoDTORequest contactInfoDTORequest){
        ContactInfoDTORequest createdContactInfo = contactInfoServiceImp.createContactInfo(contactInfoDTORequest);

        URI location = URI.create(String.format("/contact-infos/%d", createdContactInfo.getId()));
        return ResponseEntity.created(location).body(createdContactInfo);
    }

    @PutMapping("/contact-info")
    public ResponseEntity<ContactInfoDTO> updateContactInfo(@RequestBody ContactInfoDTORequest contactInfoDTORequest){
        ContactInfoDTO  updatedContactInfo = contactInfoServiceImp.updateContactInfo(contactInfoDTORequest);
        return ResponseEntity.ok(updatedContactInfo);
    }

    @DeleteMapping("/{contactInfoId}")
    public ResponseEntity<Void> deleteContactInfo(@PathVariable int contactInfoId){
        contactInfoServiceImp.deleteContactInfo(contactInfoId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/by-email")
    public ResponseEntity<ContactInfoDTO> getContactInfoByEmail(@RequestParam String email){
        ContactInfoDTO contactInfoDTO = contactInfoServiceImp.getByEmail(email);
        return ResponseEntity.ok(contactInfoDTO);
    }

    @GetMapping("/by-phone")
    public ResponseEntity<ContactInfoDTO> getContactInfoByPhone(@RequestParam String phone){
        ContactInfoDTO contactInfoDTO = contactInfoServiceImp.getByPhone(phone);
        return ResponseEntity.ok(contactInfoDTO);
    }
}