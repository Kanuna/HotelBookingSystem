package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.models.ContactInfoHotel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactInfoHotelRepository {
    Optional<ContactInfoHotel> findByHotelId(int hotel_id);
}