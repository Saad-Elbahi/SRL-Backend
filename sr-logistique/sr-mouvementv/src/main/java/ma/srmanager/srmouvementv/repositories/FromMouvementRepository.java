package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.model.FromMouvement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FromMouvementRepository extends JpaRepository<FromMouvement,Long> {
    List<FromMouvement> findByVehiculeRouteId(Long vehiculeRouteId);

}
