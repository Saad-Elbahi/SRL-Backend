package ma.srmanager.srjwt.repositories;

import ma.srmanager.srjwt.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);

    AppUser findFirstById(Long id);
}
