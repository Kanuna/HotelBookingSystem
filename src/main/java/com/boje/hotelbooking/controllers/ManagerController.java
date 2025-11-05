package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.ManagerDTO;
import com.boje.hotelbooking.dtoRequest.ManagerDTORequest;
import com.boje.hotelbooking.serviceImp.ManagerServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerServiceImp managerServiceImp;

    public ManagerController(ManagerServiceImp managerServiceImp) {
        this.managerServiceImp = managerServiceImp;
    }


    @PostMapping("/manager")
    public ResponseEntity<ManagerDTORequest> createManager(@RequestBody ManagerDTORequest managerDTORequest){
        ManagerDTORequest createdManager =  managerServiceImp.createManager(managerDTORequest);

        URI location = URI.create(String.format("/managers/%d", createdManager.getId()));
        return ResponseEntity.created(location).body(createdManager);
    }


    @PutMapping("/manager")
    public ResponseEntity<ManagerDTO> updateManager(@RequestBody ManagerDTORequest managerDTORequest){
        ManagerDTO updatedManager =  managerServiceImp.updateManager(managerDTORequest);
        return  ResponseEntity.ok().body(updatedManager);
    }

    @DeleteMapping("/{managerId}")
    public ResponseEntity<Void>  deleteManager(@PathVariable int managerId){
        managerServiceImp.deleteManager(managerId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/by-email")
    public ResponseEntity<ManagerDTO> getManagerByEmail(@RequestParam String email){
        ManagerDTO manager = managerServiceImp.findManagerByEmail(email);
        return manager != null
                ? ResponseEntity.ok(manager)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-phone")
    public ResponseEntity<ManagerDTO> getManagerByPhone(@RequestParam String phone){
        ManagerDTO manager = managerServiceImp.findManagerByPhoneNumber(phone);
        return manager != null
                ?  ResponseEntity.ok(manager)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{hotelId}/managers")
    public ResponseEntity<List<ManagerDTO>> getManagersByHotelId(@PathVariable int hotelId){
        List<ManagerDTO> managers = managerServiceImp.findManagerByHotelId(hotelId);
        return managers.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(managers);
    }
}