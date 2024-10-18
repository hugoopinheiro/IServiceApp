package br.com.crisgo.iservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity(name = "orders")
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "orderId")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne // One user can have many orders
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne // One service can have many orders
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "status")
    private String status;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;
}
