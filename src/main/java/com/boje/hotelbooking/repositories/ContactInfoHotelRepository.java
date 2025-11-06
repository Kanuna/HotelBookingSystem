package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.models.ContactInfoHotel;
import com.boje.hotelbooking.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactInfoHotelRepository extends JpaRepository<ContactInfoHotel, Integer> {
    Optional<ContactInfoHotel> findByHotelId(int hotel_id);

    List<ContactInfoHotel> hotel(Hotel hotel);
}