package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.RequestReviewsDTO;
import br.com.crisgo.iservice.DTO.response.ResponseReviewsDTO;
import br.com.crisgo.iservice.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/review/")
@Validated
public class ReviewsController {

    private final ReviewsService reviewService;

    @Autowired
    public ReviewsController(ReviewsService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("user/{userId}/product/{productId}")
    public ResponseEntity<ResponseReviewsDTO> createReview(
            @PathVariable Long productId,
            @PathVariable Long userId,
            @RequestBody @Validated RequestReviewsDTO requestReviewsDTO) {
        ResponseReviewsDTO createdReview = reviewService.createReview(requestReviewsDTO, productId, userId);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ResponseReviewsDTO>> findAllReviews(@PathVariable Long productId) {
        List<ResponseReviewsDTO> reviews = reviewService.findByProduct(productId);
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseReviewsDTO> updateReview(
            @PathVariable Long id,
            @RequestBody @Validated RequestReviewsDTO requestReviewsDTO) {
        ResponseReviewsDTO updatedReview = reviewService.updateReview(id, requestReviewsDTO);
        return ResponseEntity.ok(updatedReview);
    }
}
