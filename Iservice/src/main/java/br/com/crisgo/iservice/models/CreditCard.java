package br.com.crisgo.iservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "creditCard")
@Table(name = "creditCard")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "creditCardId")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_card_id")
    private Long creditCardId;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "owner")
    private String owner;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    // Never store CVV

    @Column(name = "user_id")
    private Integer userId;
}
