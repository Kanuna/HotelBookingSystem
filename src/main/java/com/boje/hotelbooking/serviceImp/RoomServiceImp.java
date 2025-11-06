package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.RoomDTO;
import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.dtoRequest.RoomDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Booking;
import com.boje.hotelbooking.models.Room;
import com.boje.hotelbooking.repositories.BookingRepository;
import com.boje.hotelbooking.repositories.HotelRepository;
import com.boje.hotelbooking.repositories.RoomRepository;
import com.boje.hotelbooking.services.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImp implements RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final EntityMapper entityMapper;

    public RoomServiceImp(RoomRepository roomRepository,
                          BookingRepository bookingRepository,
                          HotelRepository hotelRepository,
                          EntityMapper entityMapper) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public RoomDTORequest createRoom(RoomDTO roomDTO) {
        Room room = entityMapper.toRoom(roomDTO);

        if (roomDTO.getHotel_id() != null){
           Hotel hotel = hotelRepository.findById(roomDTO.getHotel_id())
                   .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " +  roomDTO.getHotel_id()));
           room.setHotel(hotel);
        }

        Room createdRoom =  roomRepository.save(room);

        return entityMapper.toRoomDTORequest(createdRoom);
    }

    @Override
    public RoomDTO updateRoom(RoomDTORequest roomDTORequest) {
        Room room = roomRepository.findById(roomDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " +  roomDTORequest.getId()));

        if(roomDTORequest.getBooking_ids() != null) {
            List<Booking> bookings = bookingRepository.findAllById(roomDTORequest.getBooking_ids());
            bookings.forEach(b -> b.setRoom(room));
            room.setBookings(bookings);
        }

        room.setHasKitchen(roomDTORequest.isHasKitchen());
        room.setNumberOfBeds(roomDTORequest.getNumberOfBeds());
        room.setPrice(roomDTORequest.getPrice());
        room.setOccupied(roomDTORequest.getOccupied());
        room.setRoomSize(roomDTORequest.getRoomSize());

        Room updtedRoom = roomRepository.save(room);

        return entityMapper.toRoomDTO(updtedRoom);
    }

    @Override
    public void deleteRoom(int room_id) {
        if (!roomRepository.existsById(room_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + room_id);
        }

        roomRepository.deleteById(room_id);
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