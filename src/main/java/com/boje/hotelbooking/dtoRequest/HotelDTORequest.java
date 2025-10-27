package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.HotelDTO;
import lombok.Getter;

@Getter
public class HotelDTORequest extends HotelDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}