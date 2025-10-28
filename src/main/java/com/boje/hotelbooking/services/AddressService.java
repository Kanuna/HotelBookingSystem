package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.AddressDTO;
import com.boje.hotelbooking.dtoRequest.AddressDTORequest;

public interface AddressService {
    AddressDTORequest createAddress(int hotel_id, AddressDTORequest addressDTORequest);
    AddressDTORequest updateAddress(AddressDTORequest addressDTORequest);
    boolean deleteAddress(int address_id);
     AddressDTO findAddressByHotelId(int hotel_id);
}