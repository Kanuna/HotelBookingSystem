package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.RoomDTO;
import com.boje.hotelbooking.dtoRequest.RoomDTORequest;
import com.boje.hotelbooking.models.Room;
import java.util.List;

public interface RoomService {
    RoomDTORequest createRoom(RoomDTO roomDTO);
    RoomDTO updateRoom(RoomDTORequest roomDTORequest);
    void deleteRoom(int room_id);

    List<RoomDTO> findByHotelId(int  hotel_id);
    List<RoomDTO> findByHotelIdAndOccupied(int hotel_id, Room.Occupied occupied);
}