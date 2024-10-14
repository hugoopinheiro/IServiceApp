package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
