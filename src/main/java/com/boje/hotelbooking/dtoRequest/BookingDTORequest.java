package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.BookingDTO;
import lombok.Getter;

@Getter
public class BookingDTORequest extends BookingDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}