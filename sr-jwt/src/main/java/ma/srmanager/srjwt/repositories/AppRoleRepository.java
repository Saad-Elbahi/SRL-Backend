package ma.srmanager.srjwt.repositories;

import ma.srmanager.srjwt.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {

     Optional<AppRole> findByRoleName(String roleName);
}
