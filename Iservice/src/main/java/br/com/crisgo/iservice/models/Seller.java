package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.RequestAddress;
import br.com.crisgo.iservice.DTO.RequestSeller;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
@Entity(name = "seller")
@Table(name = "seller")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "sellerId")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)  // Cascade all operations to Address
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Column(name = "description", nullable = false)
    private String sellerDescription;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Seller(RequestSeller requestSeller) {
        this.name = requestSeller.name();
        this.email = requestSeller.email();
        this.password = requestSeller.password();
        this.phone = requestSeller.phone();
        this.sellerDescription = getSellerDescription();
        this.createdAt = requestSeller.createdAt();

    }

    public Long getId() {return this.sellerId;}
}
