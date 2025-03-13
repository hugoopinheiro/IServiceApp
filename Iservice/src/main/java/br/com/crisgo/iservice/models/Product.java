package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.request.RequestProductDTO;
import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "productId")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name; // Use camelCase for consistency

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price; // Use Double for currency representation

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    // Set the createdAt timestamp before persisting
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
