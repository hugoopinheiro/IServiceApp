package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.request.RequestAddressDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
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
    public Address() {}

    public Address(RequestAddressDTO dto) {
        this.cep = dto.getCep();
        this.complement = dto.getComplement();
        this.houseNumber = dto.getHouseNumber();
        this.street = dto.getStreet();
        this.state = dto.getState();
    }
}
