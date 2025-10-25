package com.boje.hotelbooking.dtoRequest;

import lombok.Getter;

@Getter
public class ReviewDTORequest {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}