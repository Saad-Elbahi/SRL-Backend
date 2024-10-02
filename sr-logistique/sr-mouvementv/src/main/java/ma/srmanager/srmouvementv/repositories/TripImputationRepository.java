package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.model.Affaire;
import ma.srmanager.srmouvementv.model.TripImputation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TripImputationRepository extends JpaRepository<TripImputation, Long> {
    List<TripImputation> findByVehiculeRouteId(Long vehiculeRouteId);
    List<TripImputation> findByAffaire(Affaire affaire);


}
