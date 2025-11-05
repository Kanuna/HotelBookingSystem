package com.boje.hotelbooking.controllers;

import com.boje.hotelbooking.dto.ReviewDTO;
import com.boje.hotelbooking.dtoRequest.ReviewDTORequest;
import com.boje.hotelbooking.serviceImp.ReviewServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewServiceImp reviewServiceImp;

    public ReviewController(ReviewServiceImp reviewServiceImp) {
        this.reviewServiceImp = reviewServiceImp;
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewDTORequest> createReview(@RequestBody ReviewDTORequest reviewDTORequest){
        ReviewDTORequest createdReview = reviewServiceImp.createReview(reviewDTORequest);

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


    @GetMapping("/{hotelId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews(
            @PathVariable int hotelId,
            @RequestParam(required = false) Double starRating) {

        List<ReviewDTO> reviews = (starRating != null)
                ? reviewServiceImp.findByHotelIdAndRating(hotelId, starRating)
                : reviewServiceImp.findByHotelId(hotelId);

        return reviews.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(reviews);
    }
}