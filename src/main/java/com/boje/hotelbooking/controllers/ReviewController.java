package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.HotelDTO;
import com.boje.hotelbooking.dto.ReviewDTO;
import com.boje.hotelbooking.dtoRequest.ReviewDTORequest;
import com.boje.hotelbooking.models.Review;
import com.boje.hotelbooking.serviceImp.ReviewServiceImp;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@PermitAll
@EnableMethodSecurity
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewServiceImp reviewServiceImp;

    public ReviewController(ReviewServiceImp reviewServiceImp) {
        this.reviewServiceImp = reviewServiceImp;
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewDTORequest> createReview(@RequestBody ReviewDTO reviewDTO){
        ReviewDTORequest createdReview = reviewServiceImp.createReview(reviewDTO);

        URI location = URI.create(String.format("/reviews/%d",createdReview.getId()));
        return ResponseEntity.created(location).body(createdReview);
    }

    @PutMapping("/review")
    public ResponseEntity<ReviewDTO> updateReview(@RequestBody ReviewDTORequest reviewDTORequest){
        ReviewDTO updatedReview = reviewServiceImp.updateReview(reviewDTORequest);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable int reviewId){
        reviewServiceImp.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/hotel/{hotelId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByHotelAndRating(
            @PathVariable int hotelId,
            @RequestParam(required = false) Double starRating) {

        List<ReviewDTO> reviews = (starRating != null)
                ? reviewServiceImp.findByHotelIdAndRating(hotelId, starRating)
                : reviewServiceImp.findByHotelId(hotelId);

        return reviews.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(reviews);
    }


    @GetMapping("/user/{userId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUserId(
            @PathVariable int userId) {
        List<ReviewDTO> reviews = reviewServiceImp.findByUserId(userId);
        return reviews.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(reviews);

    }
}