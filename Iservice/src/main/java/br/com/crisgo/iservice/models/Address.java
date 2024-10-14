package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.RequestAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "address_id")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String house_number;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String state;

    public Address(RequestAddress requestAddress) {
        this.cep = requestAddress.cep();
        this.complement = requestAddress.complement();
        this.house_number = requestAddress.house_number();
        this.neighborhood = requestAddress.neighborhood();
        this.street = requestAddress.street();
        this.state = requestAddress.state();
    }
}
