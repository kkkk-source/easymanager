package co.udea.ede.easymanager.role.io.repository;

import co.udea.ede.easymanager.role.modelo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Role,Long> {

}
