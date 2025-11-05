package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.ContactInfoDTO;
import com.boje.hotelbooking.dtoRequest.ContactInfoDTORequest;


public interface ContactInfoService {
    ContactInfoDTORequest createContactInfo(ContactInfoDTO contactInfoDTO);
    ContactInfoDTO updateContactInfo(ContactInfoDTORequest contactInfoDTORequest);
    void deleteContactInfo(int contactInfo_id);
    ContactInfoDTO getByEmail(String email);
    ContactInfoDTO getByPhone(String phone);
}
