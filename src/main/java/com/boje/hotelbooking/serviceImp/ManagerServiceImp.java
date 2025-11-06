package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.ManagerDTO;
import com.boje.hotelbooking.dtoRequest.ManagerDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.ContactInfoHotel;
import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.models.Manager;
import com.boje.hotelbooking.repositories.ContactInfoHotelRepository;
import com.boje.hotelbooking.repositories.HotelRepository;
import com.boje.hotelbooking.repositories.ManagerRepository;
import com.boje.hotelbooking.services.ManagerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImp implements ManagerService {
    private final ManagerRepository managerRepository;
    private final HotelRepository hotelRepository;
    private final ContactInfoHotelRepository contactInfoHotelRepository;
    private final EntityMapper entityMapper;

    public  ManagerServiceImp(ManagerRepository managerRepository,
                              HotelRepository hotelRepository,
                              ContactInfoHotelRepository contactInfoHotelRepository,
                              EntityMapper entityMapper) {
        this.managerRepository = managerRepository;
        this.hotelRepository = hotelRepository;
        this.contactInfoHotelRepository = contactInfoHotelRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public ManagerDTORequest createManager(ManagerDTO managerDTO) {
        Manager manager = entityMapper.toManager(managerDTO);
        Manager createdManager = managerRepository.save(manager);

        return entityMapper.toManagerDTORequest(createdManager);
    }

    @Override
    public ManagerDTO updateManager(ManagerDTORequest managerDTORequest) {
        Manager manager = managerRepository.findById(managerDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + managerDTORequest.getId()));

        Hotel hotel = hotelRepository.findById(managerDTORequest.getHotel_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + managerDTORequest.getHotel_id()));

        ContactInfoHotel contactInfoHotel = contactInfoHotelRepository.findById(managerDTORequest.getContactInfoHotel_id())
                        .orElseThrow(() -> new ResourceNotFoundException("ContactInfoHotel not found with id: " + managerDTORequest.getContactInfoHotel_id()));

        manager.setHotel(hotel);
        manager.setEmail(managerDTORequest.getEmail());
        manager.setContactInfoHotel(contactInfoHotel);
        manager.setPhoneNumber(managerDTORequest.getPhoneNumber());
        manager.setFullName(managerDTORequest.getFullName());

        Manager updatedManager = managerRepository.save(manager);

        return  entityMapper.toManagerDTORequest(updatedManager);
    }

    @Override
    public void deleteManager(int manager_id) {
        if (!managerRepository.existsById(manager_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + manager_id);
        }

        managerRepository.deleteById(manager_id);
    }

    @Override
    public ManagerDTO findManagerByEmail(String email) {
        Manager manager = managerRepository.findManagerByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with email: " +  email));

        return entityMapper.toManagerDTO(manager);
    }

    @Override
    public ManagerDTO findManagerByPhoneNumber(String phoneNumber) {
        Manager manager = managerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with phoneNumber: " +  phoneNumber));

        return entityMapper.toManagerDTO(manager);
    }

    @Override
    public List<ManagerDTO> findManagerByHotelId(int hotel_id) {
        List<Manager> managers = managerRepository.findManagerByHotelId(hotel_id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with hotelId: " +  hotel_id));

        return managers.stream()
                .map(entityMapper::toManagerDTO)
                .collect(Collectors.toList());
    }
}