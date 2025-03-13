package br.com.crisgo.iservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity(name = "reviews")
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "reviewId")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "rating")
    private Integer rating; // Use Integer or custom Rating enum

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
    @PrePersist
    protected void onCreate() {
        this.reviewDate = LocalDateTime.now();
    }
}
