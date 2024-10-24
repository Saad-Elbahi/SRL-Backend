package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.FromMouvementUpdateDTO;
import ma.srmanager.srmouvementv.model.FromMouvement;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface FromMouvementService {

     FromMouvement save(FromMouvementUpdateDTO dto) throws IOException;

     Optional<FromMouvement> findById(Long id);

     List<FromMouvement> findAll();

     void deleteById(Long id);

     List<FromMouvement> getFromMouvementsByVehiculeRouteId(Long vehiculeRouteId);

     FromMouvement updateFromMouvement(FromMouvementUpdateDTO dto) throws IOException;

}
