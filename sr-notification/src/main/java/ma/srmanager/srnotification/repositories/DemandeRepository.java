package ma.srmanager.srnotification.repositories;

import ma.srmanager.srnotification.entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Long> {

    List<Demande> findAllByDestinationId(Long destinationId);

    List<Demande> findAllByDestinationRole(String destinationRole);
}
