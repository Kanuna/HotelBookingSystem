package com.boje.hotelbooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boje.hotelbooking.models.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
