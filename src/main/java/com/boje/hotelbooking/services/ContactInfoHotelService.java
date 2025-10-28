package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.ContactInfoHotelDTO;
import com.boje.hotelbooking.dtoRequest.ContactInfoHotelDTORequest;
import com.boje.hotelbooking.models.ContactInfoHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInfoHotelService {
    ContactInfoHotelDTORequest createContactInfoHotel(ContactInfoHotelDTORequest contactInfoHotelDTORequest);
    ContactInfoHotelDTORequest updateContactInfoHotel(ContactInfoHotelDTORequest contactInfoHotelDTORequest);
    boolean deleteContactInfoHotel(int contact_id);
    ContactInfoHotelDTO findByHotelId(int hotel_id);
}