package br.com.crisgo.iservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "bankAccountId")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private Long bankAccountId;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "agency")
    private String agency;

    @OneToOne(mappedBy = "bankAccount")
    private Seller seller;
}

