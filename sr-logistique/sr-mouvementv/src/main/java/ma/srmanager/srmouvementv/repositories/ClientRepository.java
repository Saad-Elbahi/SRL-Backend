package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
