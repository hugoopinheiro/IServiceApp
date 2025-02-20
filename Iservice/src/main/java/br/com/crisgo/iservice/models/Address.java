package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.request.RequestAddressDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @OneToOne(mappedBy = "address")
    private User user;

    @Column(name = "street")
    private String street;

    @Column(name = "cep")
    private String cep;

    @Column(name = "complement")
    private String complement;

    @Column(name = "state")
    private String state;

    @Column(name = "house_number")
    private String houseNumber;

    public Address(RequestAddressDTO dto) {
        this.cep = dto.getCep();
        this.complement = dto.getComplement();
        this.houseNumber = dto.getHouseNumber();
        this.street = dto.getStreet();
        this.state = dto.getState();
    }
}

