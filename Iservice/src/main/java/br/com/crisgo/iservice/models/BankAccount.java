package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.models.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "bankAccount")
@Table(name = "bankAccount")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "bankAccountId")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankAccount_id")
    private Long bankAccountId;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "agency")
    private String agency;

    @ManyToOne // One seller can have many bank accounts
    @JoinColumn(name = "seller_id")
    private Seller seller;
}

