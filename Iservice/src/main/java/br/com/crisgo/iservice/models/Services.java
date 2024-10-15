package br.com.crisgo.iservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "services")
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "service_id")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long service_id;

    @Column(name = "name", nullable = false)
    private String serviceName; // Use camelCase for consistency

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price; // Use Double for currency representation

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne // Many services can belong to one seller
    @JoinColumn(name = "provider_id", nullable = false, insertable = false, updatable = false)
    private Seller seller;

    @ManyToOne // Many services can belong to one category
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    private Category category;
}
