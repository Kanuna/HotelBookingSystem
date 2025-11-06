package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.ContactInfoHotelDTO;
import com.boje.hotelbooking.dtoRequest.ContactInfoHotelDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Amenity;
import com.boje.hotelbooking.models.ContactInfoHotel;
import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.models.Manager;
import com.boje.hotelbooking.repositories.ContactInfoHotelRepository;
import com.boje.hotelbooking.repositories.HotelRepository;
import com.boje.hotelbooking.repositories.ManagerRepository;
import com.boje.hotelbooking.services.ContactInfoHotelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInfoHotelServiceImp implements ContactInfoHotelService {
    private final ContactInfoHotelRepository contactInfoHotelRepository;
    private final HotelRepository hotelRepository;
    private final ManagerRepository  managerRepository;
    private final EntityMapper entityMapper;

    public ContactInfoHotelServiceImp(ContactInfoHotelRepository contactInfoHotelRepository,
                                      HotelRepository hotelRepository,
                                      ManagerRepository  managerRepository,
                                      EntityMapper entityMapper) {
        this.contactInfoHotelRepository = contactInfoHotelRepository;
        this.hotelRepository = hotelRepository;
        this.managerRepository = managerRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public ContactInfoHotelDTORequest createContactInfoHotel(ContactInfoHotelDTO contactInfoHotelDTO) {
        ContactInfoHotel contactInfoHotel = entityMapper.toContactInfoHotel(contactInfoHotelDTO);
        ContactInfoHotel createdContactInfoHotel = contactInfoHotelRepository.save(contactInfoHotel);

        return entityMapper.toContactInfoHotelDTORequest(createdContactInfoHotel);
    }

    @Override
    public ContactInfoHotelDTO updateContactInfoHotel(
            ContactInfoHotelDTORequest contactInfoHotelDTORequest) {
        ContactInfoHotel contactInfoHotel = contactInfoHotelRepository.findById(contactInfoHotelDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("ContactInfoHotel not found with id: " + contactInfoHotelDTORequest.getId()));

        Hotel hotel = hotelRepository.findById(contactInfoHotelDTORequest.getHotel_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + contactInfoHotelDTORequest.getHotel_id()));

        //Managers
        if (contactInfoHotelDTORequest.getManager_ids() != null) {
            List<Manager> managers = managerRepository.findAllById(contactInfoHotelDTORequest.getManager_ids());
            managers.forEach(m -> m.setContactInfoHotel(contactInfoHotel));
            contactInfoHotel.setManagers(managers);
        }

        contactInfoHotel.setHotel(hotel);
        contactInfoHotel.setHotelPhoneNumber(contactInfoHotelDTORequest.getHotelPhoneNumber());
        contactInfoHotel.setHotelEmail(contactInfoHotelDTORequest.getHotelEmail());

        ContactInfoHotel updatedContactInfoHotel = contactInfoHotelRepository.save(contactInfoHotel);

        return  entityMapper.toContactInfoHotelDTO(updatedContactInfoHotel);
    }

    @Override
    public void deleteContactInfoHotel(int contact_id) {
        try{
            contactInfoHotelRepository.deleteById(contact_id);

        }
        catch(Exception e){

        }
    }

    @Override
    public ContactInfoHotelDTO findByHotelId(int hotel_id) {
        ContactInfoHotel contactInfoHotel = contactInfoHotelRepository.findByHotelId(hotel_id)
                .orElseThrow(() -> new ResourceNotFoundException("ContactInfoHotel not found with id: " + hotel_id));

        return entityMapper.toContactInfoHotelDTORequest(contactInfoHotel);
    }
}