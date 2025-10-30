package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.HotelDTO;
import com.boje.hotelbooking.dto.RoomDTO;
import com.boje.hotelbooking.dtoRequest.RoomDTORequest;
import com.boje.hotelbooking.models.Room;
import java.util.List;

public interface RoomService {
    RoomDTORequest createRoom(RoomDTORequest roomDTORequest);
    RoomDTORequest updateRoom(int room_id, RoomDTO roomDTO);
    void deleteRoom(int room_id);

    List<RoomDTO> findByHotelId(int  hotel_id);
    List<RoomDTO> findByHotelIdAndOccupied(int hotel_id, Room.Occupied occupied);
}