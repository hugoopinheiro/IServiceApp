package br.com.crisgo.iservice.repositorys;

import br.com.crisgo.iservice.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
