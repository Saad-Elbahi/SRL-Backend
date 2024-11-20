package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.entities.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot,Long> {
}
