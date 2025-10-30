package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.HotelDTO;
import com.boje.hotelbooking.dtoRequest.AmenityDTORequest;
import com.boje.hotelbooking.dtoRequest.HotelDTORequest;
import java.util.List;;

public interface HotelService {
    HotelDTORequest createHotel(HotelDTORequest hotelDTORequest);
    HotelDTORequest updateHotel(int hotel_id, HotelDTO hotelDTO);
    void deleteHotel(int hotel_id);
    HotelDTO findByName(String hotelName);

    List<HotelDTO> findByAddressZipCode(short zipCode);
    List<HotelDTO> findByAddressCity(String city);
    List<HotelDTO> findByAddressRegion(String region);

    List<HotelDTO> findByFranchise(String franchise);
    List<HotelDTO> findByAmenities(List<AmenityDTORequest> amenityDTORequests);
}
