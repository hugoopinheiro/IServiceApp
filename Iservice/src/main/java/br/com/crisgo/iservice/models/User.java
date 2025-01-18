package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(of = "userId")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "contact", nullable = false)
    private String contact;

    @OneToOne(mappedBy = "user")
    private Seller seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

//    private List<Permission> permissions;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

//    public User(RequestUserDTO requestUserDTO) {
//        this.name = requestUserDTO.getName();
//        this.userName = requestUserDTO.getUserName();
//        this.email = requestUserDTO.getEmail();
//        this.password = requestUserDTO.getPassword();
//        this.contact = requestUserDTO.getContact();
//
//        // Map AddressDTO to Address entity if provided
//        if (requestUserDTO.getAddress() != null) {
//            this.address = new Address();
//            this.address.setStreet(requestUserDTO.getAddress().getStreet());
//            this.address.setCep(requestUserDTO.getAddress().getCep());
//            this.address.setComplement(requestUserDTO.getAddress().getComplement());
//            this.address.setState(requestUserDTO.getAddress().getState());
//            this.address.setHouseNumber(requestUserDTO.getAddress().getHouseNumber());
//        }
    }
//    public ResponseUserDTO toResponseDTO() {
//        ResponseUserDTO dto = new ResponseUserDTO();
//        dto.setUser_id(this.userId);
//        dto.setName(this.name);
//        dto.setEmail(this.email);
//        dto.setPhone(this.phone);
//        dto.setCreatedAt(this.createdAt);
//        return dto;
//    }

//    public List<String> getRoles() {
//        return permissions.stream()
//                .map(Permission::getDescription)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.permissions;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
