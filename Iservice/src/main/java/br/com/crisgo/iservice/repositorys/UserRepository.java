package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.userName = :userName")
//    User findByUsername(@Param("userName") String userName);
//    @Query("SELECT u FROM User u")
///    List<User> findAllUsers();

}
