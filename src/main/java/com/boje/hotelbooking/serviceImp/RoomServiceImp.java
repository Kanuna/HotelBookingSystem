package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.RoomDTO;
import com.boje.hotelbooking.dtoRequest.RoomDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Room;
import com.boje.hotelbooking.repositories.RoomRepository;
import com.boje.hotelbooking.services.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImp implements RoomService {
    private final RoomRepository roomRepository;
    private final EntityMapper entityMapper;

    public RoomServiceImp(RoomRepository roomRepository, EntityMapper entityMapper) {
        this.roomRepository = roomRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public RoomDTORequest createRoom(RoomDTORequest roomDTORequest) {
        Room room = entityMapper.toRoom(roomDTORequest);
        Room createdRoom =  roomRepository.save(room);

        return entityMapper.toRoomDTORequest(createdRoom);
    }

    @Override
    public RoomDTORequest updateRoom(RoomDTORequest roomDTORequest) {
        Room room = roomRepository.findById(roomDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " +  roomDTORequest.getId()));

        room.setBookings(roomDTORequest.getBookings());
        room.setHasKitchen(roomDTORequest.isHasKitchen());
        room.setNumberOfBeds(roomDTORequest.getNumberOfBeds());
        room.setPrice(roomDTORequest.getPrice());
        room.setOccupied(roomDTORequest.getOccupied());
        room.setRoomSize(roomDTORequest.getRoomSize());

        Room updtedRoom = roomRepository.save(room);

        return entityMapper.toRoomDTORequest(updtedRoom);
    }

    @Override
    public boolean deleteRoom(int room_id) {
        try{
            roomRepository.deleteById(room_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<RoomDTO> findByHotelId(int hotel_id) {
        List<Room> rooms = roomRepository.findByHotelId(hotel_id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " +  hotel_id));

        return rooms.stream()
                .map(entityMapper::toRoomDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> findByHotelIdAndOccupied(int hotel_id, Room.Occupied occupied) {
        List<Room> rooms = roomRepository.findByHotelIdAndOccupied(hotel_id, occupied)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " +  hotel_id));

        return rooms.stream()
                .map(entityMapper::toRoomDTO)
                .collect(Collectors.toList());
    }
}