package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.Product;
import br.com.crisgo.iservice.models.Reviews;
import br.com.crisgo.iservice.models.User;
import br.com.crisgo.iservice.repositorys.ProductRepository;
import br.com.crisgo.iservice.repositorys.ReviewsRepository;
import br.com.crisgo.iservice.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    private final ProductRepository productRepository;
    private final ReviewsRepository reviewsRepository;
    private final UserRepository userRepository;
    @Autowired
    public ReviewsService(ProductRepository productRepository, ReviewsRepository reviewsRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.reviewsRepository = reviewsRepository;
        this.userRepository = userRepository;
    }
    public Reviews createReview(Reviews reviews, Long productId, Long userId) {
        // Find the product by ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        // Link the review to the product
        reviews.setProduct(product);
        // link the review to the user
        reviews.setUser(user);
        // Save the product with the linked seller
        return reviewsRepository.save(reviews);
    }

    public List<Reviews> findByProduct(Long productId) {
        // Check if the product exists, otherwise throw an exception
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("produto não encontrado"));
        // Get the list of reviews for the product
        List<Reviews> reviews = reviewsRepository.findByProduct(product);

        // If no reviews are found, throw an exception or handle it accordingly
        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("reviews não encontradas para este produto");
        }

        return reviews;
    }
    @Transactional
    public void deleteById(Long id) {
        if (!reviewsRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto de ID " + id + " não encontrado");
        }
        reviewsRepository.deleteById(id);
    }


    @Transactional
    public Reviews updateReview(Long id, Reviews reviewsDetails ) {
        // Find existing user
        Reviews existingReview = reviewsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto de ID " + id + " não encontrado"));

        // Update fields
        existingReview.setComments(reviewsDetails.getComments());  // example field, adjust based on your model
        existingReview.setRating(reviewsDetails.getRating());

        // Save the updated seller
        return reviewsRepository.save(existingReview);
    }

}
