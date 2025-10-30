package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.RoomDTO;
import com.boje.hotelbooking.dtoRequest.RoomDTORequest;
import com.boje.hotelbooking.models.Room;
import com.boje.hotelbooking.serviceImp.RoomServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomServiceImp roomServiceImp;

    public RoomController(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
    }


    @PostMapping("/room")
    public ResponseEntity<RoomDTORequest> createRoom(@RequestBody RoomDTORequest roomDTORequest) {
        RoomDTORequest createdRoom = roomServiceImp.createRoom(roomDTORequest);

        URI location = URI.create(String.format("/rooms/%d", createdRoom.getId()));
        return ResponseEntity.created(location).body(createdRoom);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable int roomId,
                                              @RequestBody RoomDTO roomDTO) {
        RoomDTO updatedRoom = roomServiceImp.updateRoom(roomId, roomDTO);
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