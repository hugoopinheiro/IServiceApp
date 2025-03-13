package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
