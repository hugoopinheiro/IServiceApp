package br.com.crisgo.iservice.DTO.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseReviewsDTO {

    private Long reviewId;
    private Integer rating;
    private String comments;
    private LocalDateTime reviewDate;
    private String userName;    // Simplified user information
    private Long productId;
}
