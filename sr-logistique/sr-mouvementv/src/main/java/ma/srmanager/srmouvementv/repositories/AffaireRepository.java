package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.model.Affaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface AffaireRepository extends JpaRepository<Affaire, Long> {
  //  Optional<Affaire> findByNameAndGroupName(String code, String intitule);

}
