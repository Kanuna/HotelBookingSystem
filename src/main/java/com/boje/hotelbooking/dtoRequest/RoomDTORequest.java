package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.RoomDTO;
import lombok.Getter;

@Getter
public class RoomDTORequest extends RoomDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}