package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseSellerDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_account_id", referencedColumnName = "bank_account_id") // coluna in the table seller
    private BankAccount bankAccount;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "description", nullable = false)
    private String sellerDescription;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return this.sellerId;
    }
}


