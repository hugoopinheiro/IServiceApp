package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
   // boolean existsByUserId(Long userId);
}
