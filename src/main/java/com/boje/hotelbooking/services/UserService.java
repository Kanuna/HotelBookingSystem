package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.UserDTO;
import com.boje.hotelbooking.dtoRequest.UserDTORequest;
import org.springframework.data.jpa.repository.EntityGraph;


public interface UserService {
    UserDTORequest createUser(UserDTORequest userDTORequest);
    UserDTORequest updateUser(int user_id, UserDTO userDTO);
    void deleteUser(int user_id);

    UserDTO findByEmail(String email);
    UserDTO findByPhone(String phone);

    @EntityGraph(attributePaths = {"bookings", "contactInfo"})
    UserDTO findAllDataByUserId(int user_id);
}