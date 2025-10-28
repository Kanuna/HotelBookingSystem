package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByContactInfo_Email(String email);
    Optional<User> findByContactInfo_PhoneNumber(String phone);
    Optional<User> findAllDataById(int user_id);
}