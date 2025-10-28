package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    Optional<List<Room>> findByHotelId(int hotel_id);
    Optional<List<Room>> findByHotelIdAndOccupied(int hotel_id, Room.Occupied occupied);
}