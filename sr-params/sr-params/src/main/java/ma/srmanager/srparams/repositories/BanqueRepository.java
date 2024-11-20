package ma.srmanager.srparams.repositories;

import ma.srmanager.srparams.entities.Banque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanqueRepository extends JpaRepository<Banque,Long> {
}
