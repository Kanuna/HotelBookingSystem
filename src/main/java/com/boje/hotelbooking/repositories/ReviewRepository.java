package com.boje.hotelbooking.repositories;

import com.boje.hotelbooking.models.Hotel;
import com.boje.hotelbooking.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Optional<List<Review>> findReviewByHotelId(int hotel_id);
    Optional<List<Review>> findReviewByHotelIdAndRating(int hotel_id, double starRating);

    Hotel hotel(Hotel hotel);
}