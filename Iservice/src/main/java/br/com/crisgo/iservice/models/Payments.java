package br.com.crisgo.iservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity(name = "payments")
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "paymentId")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne // One order can have many payments
    @JoinColumn(name = "order_id")
    private Orders order;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_method")
    private String paymentMethod;
}

