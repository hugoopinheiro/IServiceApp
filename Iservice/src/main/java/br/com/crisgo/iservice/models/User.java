package br.com.crisgo.iservice.models;

import br.com.crisgo.iservice.DTO.RequestUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity (name = "users")
@Data
@Table(name = "users")
@EqualsAndHashCode (of = "user_id")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @CreationTimestamp
    private LocalDateTime created_at;

    public User(RequestUser dataUser) {
        this.name = dataUser.name();
        this.last_name = dataUser.last_name();
        this.email = dataUser.email();
        this.password = dataUser.password();
        this.created_at = dataUser.created_at();
    }

}
