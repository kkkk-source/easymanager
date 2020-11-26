package co.udea.ede.easymanager.user.io.repository;

import co.udea.ede.easymanager.role.modelo.Role;
import co.udea.ede.easymanager.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
