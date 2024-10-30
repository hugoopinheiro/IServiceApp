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
@EqualsAndHashCode(of = "addressId")  // Uses Java camel case style
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;  // Updated to camel case

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false, name = "house_number")
    private String houseNumber;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String state;

    public Address(RequestAddress requestAddress) {
        this.cep = requestAddress.getCep();
        this.complement = requestAddress.getComplement();
        this.houseNumber = requestAddress.getHouseNumber();
        this.street = requestAddress.getStreet();
        this.state = requestAddress.getState();
    }
}
