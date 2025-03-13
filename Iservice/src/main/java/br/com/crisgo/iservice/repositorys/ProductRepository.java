package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.crisgo.iservice.models.Seller;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
        List<Product> findBySeller(Seller seller);

        // Method to find product by name
        Optional<Product> findByName(String name);
}
