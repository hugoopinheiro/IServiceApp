package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    UserDetails findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    UserDetails findByEmail(@Param("email") String email);
}
