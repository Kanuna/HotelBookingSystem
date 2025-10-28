package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.dto.AmenityDTO;
import com.boje.hotelbooking.models.Amenity;
import com.boje.hotelbooking.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Optional<List<Hotel>> findByAddress_ZipCode(short zipCode);
    Optional<List<Hotel>> findByAddress_City(String city);
    Optional<List<Hotel>> findByAddress_Region(String region);

    Optional<List<Hotel>> findByFranchise(String franchise);
    Optional<List<Hotel>> findByAmenities(List<Amenity> amenities);
    Optional<Hotel> findByName(String hotelName);
}