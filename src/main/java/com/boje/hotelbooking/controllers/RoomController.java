package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.RoomDTO;
import com.boje.hotelbooking.dtoRequest.RoomDTORequest;
import com.boje.hotelbooking.models.Room;
import com.boje.hotelbooking.serviceImp.RoomServiceImp;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@PermitAll
@EnableMethodSecurity
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomServiceImp roomServiceImp;

    public RoomController(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
    }


    @PostMapping("/room")
    public ResponseEntity<RoomDTORequest> createRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTORequest createdRoom = roomServiceImp.createRoom(roomDTO);

        URI location = URI.create(String.format("/rooms/%d", createdRoom.getId()));
        return ResponseEntity.created(location).body(createdRoom);
    }

    @PutMapping("/room")
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTORequest roomDTORequest) {
        RoomDTO updatedRoom = roomServiceImp.updateRoom(roomDTORequest);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int roomId){
        roomServiceImp.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{hotelId}/rooms")
    public ResponseEntity<List<RoomDTO>> getRooms(@PathVariable int hotelId,
                                                  @RequestParam(required = false) Room.Occupied occupied){
        List<RoomDTO> rooms = (occupied != null)
                ? roomServiceImp.findByHotelIdAndOccupied(hotelId, occupied)
                : roomServiceImp.findByHotelId(hotelId);

        return rooms.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(rooms);
    }
}