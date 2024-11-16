package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity (name = "users")
@Data
@Table(name = "users")
@EqualsAndHashCode (of = "user_id")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")})
    private List<Permission> permissions;


    @CreationTimestamp
    private LocalDateTime createdAt;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }
    public User(RequestUserDTO dataUser) {
        this.name = dataUser.getName();
        this.email = dataUser.getEmail();
        this.password = dataUser.getPassword();
        this.userName = dataUser.getUserName();
    }
    public ResponseUserDTO toResponseDTO () {
        ResponseUserDTO dto = new ResponseUserDTO();
        dto.setId(this.user_id);
        dto.setName(this.name);
        dto.setEmail(this.email);
        dto.setPhone(this.phone);
        dto.setCreatedAt(this.createdAt);
        return dto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return this.permissions;
    }

    @Override
    public String getUsername () {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled();
    }
}
