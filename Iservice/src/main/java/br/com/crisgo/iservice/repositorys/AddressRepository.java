package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
