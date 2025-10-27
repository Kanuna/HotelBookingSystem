package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.ManagerDTO;
import lombok.Getter;


@Getter
public class ManagerDTORequest extends ManagerDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}
