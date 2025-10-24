package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.AddressDTO;
import lombok.Getter;

@Getter
public class AddressDTORequest extends AddressDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}