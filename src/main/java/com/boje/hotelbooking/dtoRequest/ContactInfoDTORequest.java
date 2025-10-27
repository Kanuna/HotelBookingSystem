package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.ContactInfoDTO;
import lombok.Getter;

@Getter
public class ContactInfoDTORequest extends ContactInfoDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}