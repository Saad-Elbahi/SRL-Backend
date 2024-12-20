package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.FromMouvementRequestDTO;
import ma.srmanager.srmouvementv.entities.FromMouvement;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface FromMouvementService {

    // FromMouvement save(FromMouvementUpdateDTO dto) throws IOException;

     Optional<FromMouvement> findById(Long id);

     List<FromMouvement> findAll();

     List<FromMouvement> updateFromMouvement(FromMouvementRequestDTO fromMouvementRequestDTO, String token) throws IOException;

     List<FromMouvement> saveFromMouvement(FromMouvementRequestDTO dto, String token) throws IOException;

     void deleteById(Long id);

     List<FromMouvement> getFromMouvementsByVehiculeRouteId(Long vehiculeRouteId);

}
