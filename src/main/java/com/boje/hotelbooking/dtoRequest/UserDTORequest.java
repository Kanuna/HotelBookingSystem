package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.UserDTO;
import lombok.Getter;

@Getter
public class UserDTORequest extends UserDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}