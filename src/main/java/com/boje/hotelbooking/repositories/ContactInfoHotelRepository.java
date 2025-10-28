package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.models.ContactInfoHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactInfoHotelRepository extends JpaRepository<ContactInfoHotel, Integer> {
    Optional<ContactInfoHotel> findByHotelId(int hotel_id);
}