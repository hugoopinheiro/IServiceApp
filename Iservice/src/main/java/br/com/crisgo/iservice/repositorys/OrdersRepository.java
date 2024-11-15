package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.Orders;
import br.com.crisgo.iservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUser(User requestUserDTO);
}
