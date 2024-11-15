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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)  // Cascade all operations to Address
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Column(name = "description", nullable = false)
    private String sellerDescription;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Seller(RequestSellerDTO requestSellerDTO) {
        this.name = requestSellerDTO.getName();
        this.email = requestSellerDTO.getEmail();
        this.password = requestSellerDTO.getPassword();
        this.phone = requestSellerDTO.getPhone();
        this.sellerDescription = requestSellerDTO.getSellerDescription();

        // Map AddressDTO to Address entity if provided
        if (requestSellerDTO.getAddress() != null) {
            this.address = new Address();
            this.address.setStreet(requestSellerDTO.getAddress().getStreet());
            this.address.setCep(requestSellerDTO.getAddress().getCep());
            this.address.setComplement(requestSellerDTO.getAddress().getComplement());
            this.address.setState(requestSellerDTO.getAddress().getState());
            this.address.setHouseNumber(requestSellerDTO.getAddress().getHouseNumber());
        }
    }


    public ResponseSellerDTO toResponseDTO() {
        ResponseSellerDTO dto = new ResponseSellerDTO();
        dto.setId(this.sellerId);
        dto.setName(this.name);
        dto.setEmail(this.email);
        dto.setPhone(this.phone);
        dto.setSellerDescription(this.sellerDescription);
        dto.setCreatedAt(this.createdAt);
        return dto;
    }


    public Long getId() {return this.sellerId;}
}
