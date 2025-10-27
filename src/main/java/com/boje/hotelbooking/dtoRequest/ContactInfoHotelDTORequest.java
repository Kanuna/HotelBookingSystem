package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.ContactInfoHotelDTO;
import lombok.Getter;

@Getter
public class ContactInfoHotelDTORequest extends ContactInfoHotelDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}