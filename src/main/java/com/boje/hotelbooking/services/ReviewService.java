package com.boje.hotelbooking.services;

import com.boje.hotelbooking.dto.HotelDTO;
import com.boje.hotelbooking.dto.ReviewDTO;
import com.boje.hotelbooking.dtoRequest.ReviewDTORequest;
import java.util.List;

public interface ReviewService {
    ReviewDTORequest createReview(ReviewDTORequest reviewDTORequest);
    ReviewDTORequest updateReview(int review_id, ReviewDTO reviewDTO);
    void deleteReview(int review_id);

    List<ReviewDTO> findByHotelId(int hotel_id);
    List<ReviewDTO> findByHotelIdAndRating(int hotel_id, double starRating);
}