package com.boje.hotelbooking.dtoRequest;

import com.boje.hotelbooking.dto.ReviewDTO;
import lombok.Getter;

@Getter
public class ReviewDTORequest extends ReviewDTO {
    private int id;

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }
}