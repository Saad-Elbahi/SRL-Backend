package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.entities.TripImputation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TripImputationRepository extends JpaRepository<TripImputation, Long> {
    List<TripImputation> findByVehiculeRouteId(Long vehiculeRouteId);

    List<TripImputation> findByAffaireCode(String affaireCode);

    List<TripImputation> findByAffaireId(Long affaireId);


}
