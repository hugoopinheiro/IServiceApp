package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
@Builder
@ToString(exclude = "address")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "contact")
    private String contact;

    @OneToOne(mappedBy = "user")
    private Seller seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "active")
    private Boolean active = true;

    private List<Permission> permissions;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if(this.role == Role.SELLER) return List.of(new  SimpleGrantedAuthority("ROLE_SELLER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_COMMON_USER"));
    }

    public List<String> getRoles() {
        return permissions.stream()
                .map(Permission::getDescription)
                .collect(Collectors.toList());
    }
    public User(String email, String password, Role role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public String getUsername() {
        return this.username; // Use the login field for authentication
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}