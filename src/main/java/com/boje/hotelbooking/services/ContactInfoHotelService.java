package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.ContactInfoHotelDTO;
import com.boje.hotelbooking.dtoRequest.ContactInfoHotelDTORequest;


public interface ContactInfoHotelService {
    ContactInfoHotelDTORequest createContactInfoHotel(ContactInfoHotelDTORequest contactInfoHotelDTORequest);
    ContactInfoHotelDTORequest updateContactInfoHotel(int contactInfoHotel_id, ContactInfoHotelDTO contactInfoHotelDTO);
    void deleteContactInfoHotel(int contact_id);
    ContactInfoHotelDTO findByHotelId(int hotel_id);
}