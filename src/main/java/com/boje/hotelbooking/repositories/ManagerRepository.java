package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Optional<Manager> findManagerByEmail(String email);
    Optional<Manager> findByPhoneNumber(String phoneNumber);

    Optional<List<Manager>> findManagerByHotelId(int hotel_id);
}