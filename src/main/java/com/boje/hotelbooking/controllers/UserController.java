package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.UserDTO;
import com.boje.hotelbooking.dtoRequest.UserDTORequest;
import com.boje.hotelbooking.serviceImp.UserServiceImp;
import org.apache.coyote.Response;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp userServiceImp;

    public  UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @PostMapping("/user")
    public ResponseEntity<UserDTORequest> createUser(@RequestBody UserDTORequest userDTORequest){
        UserDTORequest createdUser =  userServiceImp.createUser(userDTORequest);

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
    public ResponseEntity<UserDTO> getAllUserDataByUserId(@PathVariable int userId){
        UserDTO user = userServiceImp.findAllDataByUserId(userId);
        return ResponseEntity.ok(user);
    }
}