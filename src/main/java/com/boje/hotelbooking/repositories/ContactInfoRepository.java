package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo,Integer> {
}