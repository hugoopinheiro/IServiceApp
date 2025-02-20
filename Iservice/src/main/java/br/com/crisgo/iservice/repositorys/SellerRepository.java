package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    default Seller findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Seller not found"));
    }

}
