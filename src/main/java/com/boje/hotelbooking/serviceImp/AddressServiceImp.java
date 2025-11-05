package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.AddressDTO;
import com.boje.hotelbooking.dtoRequest.AddressDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Address;
import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.repositories.AddressRepository;
import com.boje.hotelbooking.repositories.HotelRepository;
import com.boje.hotelbooking.services.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {
    private final AddressRepository addressRepository;
    private final HotelRepository hotelRepository;
    private final EntityMapper entityMapper;

    public AddressServiceImp(AddressRepository addressRepository,
                             HotelRepository hotelRepository,
                             EntityMapper entityMapper) {
        this.addressRepository = addressRepository;
        this.hotelRepository = hotelRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public AddressDTORequest createAddress(int hotel_id, AddressDTO addressDTO) {
        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotel_id));

        Address address = entityMapper.toAddress(addressDTO);

        address.setHotel(hotel);
        hotel.setAddress(address);

        Address createdAddress = addressRepository.save(address);

        return entityMapper.toAddressDTORequest(createdAddress);
    }

    @Override
    public AddressDTO updateAddress(AddressDTORequest addressDTORequest) {
        Address address = addressRepository.findById(addressDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressDTORequest.getId()));

        address.setRegion(addressDTORequest.getRegion());
        address.setCity(addressDTORequest.getCity());
        address.setZipCode(addressDTORequest.getZipCode());
        address.setStreet(addressDTORequest.getStreet());
        address.setHotel(entityMapper.toHotel(addressDTORequest.getHotel()));

        Address updatedAddress = addressRepository.save(address);

        return entityMapper.toAddressDTO(updatedAddress);
    }

    @Override
    public void deleteAddress(int address_id) {
        if (!addressRepository.existsById(address_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + address_id);
        }

        addressRepository.deleteById(address_id);
    }

    @Override
    public AddressDTO findAddressByHotelId(int hotel_id) {
        Address address = addressRepository.findByHotelId(hotel_id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + hotel_id));

        return entityMapper.toAddressDTO(address);
    }
}