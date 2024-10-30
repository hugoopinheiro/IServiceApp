package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.models.Product;
import br.com.crisgo.iservice.models.Reviews;
import br.com.crisgo.iservice.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@Validated
public class ReviewsController {
    private final ReviewsService reviewService;
    @Autowired
    public ReviewsController(ReviewsService reviewService) {this.reviewService = reviewService;}

    @PostMapping("user/{userId}/product/{productId}")
    public ResponseEntity createReview(@PathVariable Long productId, @PathVariable Long userId, @RequestBody Reviews reviews){
        Reviews createdReview = reviewService.createReview(reviews, productId, userId);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Reviews>> findAllReviews(@PathVariable Long productId) {
        List<Reviews> reviews = reviewService.findByProduct(productId);
        return ResponseEntity.ok(reviews);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reviews> updateReview(@PathVariable Long id, @RequestBody @Validated Reviews reviewsDetails) {
        Reviews updatedReview = reviewService.updateReview(id, reviewsDetails);
        return ResponseEntity.ok(updatedReview);
    }

}
