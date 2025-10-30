package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.ReviewDTO;
import com.boje.hotelbooking.dtoRequest.ReviewDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Review;
import com.boje.hotelbooking.repositories.ReviewRepository;
import com.boje.hotelbooking.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImp implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final EntityMapper entityMapper;

    public ReviewServiceImp(ReviewRepository reviewRepository, EntityMapper entityMapper) {
        this.reviewRepository = reviewRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public ReviewDTORequest createReview(ReviewDTORequest reviewDTORequest) {
        Review  review = entityMapper.toReview(reviewDTORequest);
        Review createdReview = reviewRepository.save(review);

        return entityMapper.toReviewDTORequest(createdReview);
    }

    @Override
    public ReviewDTORequest updateReview(int review_id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(review_id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with phone: " +  review_id));

        review.setTitle(reviewDTO.getTitle());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());

        Review updatedReview = reviewRepository.save(review);

        return  entityMapper.toReviewDTORequest(updatedReview);
    }

    @Override
    public void deleteReview(int review_id) {
        if (!reviewRepository.existsById(review_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + review_id);
        }

        reviewRepository.deleteById(review_id);
    }

    @Override
    public List<ReviewDTO> findByHotelId(int hotel_id) {
        List<Review> reviews = reviewRepository.findReviewByHotelId(hotel_id)
                .orElseThrow(() -> new ResourceNotFoundException("No reviews found with hotel id: " + hotel_id));

        return reviews.stream()
                .map(entityMapper::toReviewDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByHotelIdAndRating(int hotel_id, double starRating) {
        List<Review> reviews = reviewRepository.findReviewByHotelIdAndRating(hotel_id, starRating)
                .orElseThrow(() -> new ResourceNotFoundException("No reviews found with hotel id: " + hotel_id));

        return reviews.stream()
                .map(entityMapper::toReviewDTO)
                .collect(Collectors.toList());
    }
}