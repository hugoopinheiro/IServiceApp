package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RequestReviewsDTO;
import br.com.crisgo.iservice.DTO.response.ResponseReviewsDTO;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.mapper.Mapper;
import br.com.crisgo.iservice.models.Product;
import br.com.crisgo.iservice.models.Reviews;
import br.com.crisgo.iservice.models.User;
import br.com.crisgo.iservice.repositorys.ProductRepository;
import br.com.crisgo.iservice.repositorys.ReviewsRepository;
import br.com.crisgo.iservice.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewsService {

    private final ProductRepository productRepository;
    private final ReviewsRepository reviewsRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    @Autowired
    public ReviewsService(ProductRepository productRepository,Mapper mapper, ReviewsRepository reviewsRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.reviewsRepository = reviewsRepository;
        this.userRepository = userRepository;
    }

    public ResponseReviewsDTO createReview(RequestReviewsDTO requestReviewsDTO, Long productId, Long userId) {
        // Retrieve Product and User
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        // Map RequestReviewsDTO to Reviews entity
        Reviews reviews = Reviews.builder()
                .reviewDate(LocalDateTime.now())
                .comments(requestReviewsDTO.getComments())
                .rating(requestReviewsDTO.getRating())
                .product(product)
                .user(user)
                .build();

        Reviews savedReview = reviewsRepository.save(reviews);
        return mapper.map(savedReview, ResponseReviewsDTO.class);
    }

    public List<ResponseReviewsDTO> findByProduct(Long productId) {
        // Retrieve Product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        // Find reviews for the product and map to DTOs
        List<Reviews> reviews = reviewsRepository.findByProduct(product);
        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("Reviews não encontradas para este produto");
        }

        return reviews.stream()
                .map(review -> mapper.map(review, ResponseReviewsDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!reviewsRepository.existsById(id)) {
            throw new EntityNotFoundException("Review de ID " + id + " não encontrado");
        }
        reviewsRepository.deleteById(id);
    }

    @Transactional
    public ResponseReviewsDTO updateReview(Long id, RequestReviewsDTO requestReviewsDTO) {
        // Retrieve the existing review
        Reviews existingReview = reviewsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review de ID " + id + " não encontrado"));

        // Map the updated fields from RequestReviewsDTO to the existing Reviews entity
        mapper.mapOntoExistingObject(requestReviewsDTO, existingReview);

        Reviews updatedReview = reviewsRepository.save(existingReview);
        return mapper.map(updatedReview, ResponseReviewsDTO.class);
    }
}
