package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.Product;
import br.com.crisgo.iservice.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
    List<Reviews> findByProduct(Product product);
}
