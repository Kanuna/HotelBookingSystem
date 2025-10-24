package com.boje.hotelbooking.dtoRequest;

import lombok.Getter;

@Getter
public class RoomDTORequest {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}