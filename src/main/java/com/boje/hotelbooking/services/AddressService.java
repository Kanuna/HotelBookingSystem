package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.AddressDTO;
import com.boje.hotelbooking.dtoRequest.AddressDTORequest;

public interface AddressService {
    AddressDTORequest createAddress(int hotel_id, AddressDTO addressDTO);
    AddressDTO updateAddress(AddressDTORequest addressDTORequest);
    void deleteAddress(int address_id);
    AddressDTO findAddressByHotelId(int hotel_id);
}