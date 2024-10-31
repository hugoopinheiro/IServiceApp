// ResponseSellerDTO.java
package br.com.crisgo.iservice.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseSellerDTO {

    private Long reviewId;
    private Integer rating; 

    @Column(name = "comments")
    private String comments;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @ManyToOne // One user can have many reviews
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne // One product can have many reviews
    @JoinColumn(name = "product_id")
    private Product product;
}
