package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.AmenityDTO;
import lombok.Getter;

@Getter
public class AmenityDTORequest extends AmenityDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}