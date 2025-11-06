package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.UserDTO;
import com.boje.hotelbooking.dto.UserResponseDTO;
import com.boje.hotelbooking.dtoRequest.UserDTORequest;
import com.boje.hotelbooking.serviceImp.UserServiceImp;
import jakarta.annotation.security.PermitAll;
import org.apache.coyote.Response;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@PermitAll
@EnableMethodSecurity
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp userServiceImp;

    public  UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @PostMapping("/user")
    public ResponseEntity<UserDTORequest> createUser(@RequestBody UserDTO userDTO){
        UserDTORequest createdUser =  userServiceImp.createUser(userDTO);

        URI location = URI.create(String.format("/users%d", createdUser.getId()));
        return ResponseEntity.created(location).body(createdUser);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTORequest userDTORequest){
        UserDTO updatedUser = userServiceImp.updateUser(userDTORequest);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId){
        userServiceImp.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/by-email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email){
        UserDTO user = userServiceImp.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/by-phone")
    public ResponseEntity<UserDTO> getUSerByPhone(@RequestParam String phone){
        UserDTO user = userServiceImp.findByPhone(phone);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getAllUserDataByUserId(@PathVariable int userId){
        UserResponseDTO user = userServiceImp.findAllDataByUserId(userId);
        return ResponseEntity.ok(user);
    }
}