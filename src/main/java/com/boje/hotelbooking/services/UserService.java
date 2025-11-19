package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.LoginRequestDTO;
import com.boje.hotelbooking.dto.UserDTO;
import com.boje.hotelbooking.dto.UserResponseDTO;
import com.boje.hotelbooking.dtoRequest.UserDTORequest;
import org.springframework.data.jpa.repository.EntityGraph;


public interface UserService {
    UserDTORequest createUser(UserDTO UserDTO);
    UserDTO updateUser(UserDTORequest UserDTORequest);
    void deleteUser(int user_id);

    UserDTO findByEmail(String email);
    UserDTO findByPhone(String phone);

    @EntityGraph(attributePaths = {"bookings", "contactInfo", "reviews"})
    UserResponseDTO findAllDataByUserId(int user_id);
    Boolean login(LoginRequestDTO request);
}