package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.ManagerDTO;
import com.boje.hotelbooking.dtoRequest.ManagerDTORequest;
import java.util.List;

public interface ManagerService {
    ManagerDTORequest createManager(ManagerDTORequest managerDTORequest);
    ManagerDTORequest updateManager(int manger_id, ManagerDTO managerDTO);
    void deleteManager(int manager_id);

    ManagerDTO findManagerByEmail(String email);
    ManagerDTO findManagerByPhoneNumber(String phoneNumber);
    List<ManagerDTO> findManagerByHotelId(int hotel_id);
}