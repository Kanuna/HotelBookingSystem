package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.ContactInfoDTO;
import com.boje.hotelbooking.dtoRequest.ContactInfoDTORequest;


public interface ContactInfoService {
    ContactInfoDTORequest createContactInfo(ContactInfoDTORequest contactInfoDTORequest);
    ContactInfoDTORequest updateContactInfo(ContactInfoDTORequest contactInfoDTORequest);
    boolean deleteContactInfo(int contactInfo_id);
    ContactInfoDTO getByEmail(String email);
    ContactInfoDTO getByPhone(String phone);
}
