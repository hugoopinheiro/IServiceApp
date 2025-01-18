package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
