package ma.srmanager.srmouvementv.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.FromMouvementRequestDTO;
import ma.srmanager.srmouvementv.model.Fournisseur;
import ma.srmanager.srmouvementv.model.FromMouvement;
import ma.srmanager.srmouvementv.model.VehiculeRoute;
import ma.srmanager.srmouvementv.models.Affaire;
import ma.srmanager.srmouvementv.repositories.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class FromMouvementServiceImpl implements FromMouvementService {

    private FromMouvementRepository fromMouvementRepository;
    private VehiculeRouteRepository vehiculeRouteRepository;
    private FournisseurRepository fournisseurRepository;
    private AffaireService affaireService;
    private static final String fileUploadDir = System.getProperty("user.home") + "/Apps/sr-manager/RESSOURCES/data/assets/images/";

//    @Override
//    public FromMouvement save(FromMouvementUpdateDTO dto) throws IOException {
//        // Create a new FromMouvement entity
//        FromMouvement fromMouvement = new FromMouvement();
//
//        // Set fields from DTO
//        fromMouvement.setAffaireId(dto.getAffaire().getId());
//        fromMouvement.setAffaireCode(dto.getAffaire().getCode());
//        fromMouvement.setFournisseurid(dto.());
//        fromMouvement.setFournisseur(dto.getFournisseur());
//        fromMouvement.setBl(dto.getBl());
//        fromMouvement.setBlMontant(dto.getBlMontant());
//        fromMouvement.setDateBl(dto.getDateBl());
//
//        // Save and return the created FromMouvement entity
//        return fromMouvementRepository.save(fromMouvement);
//    }

    @Override
    public Optional<FromMouvement> findById(Long id) {
        return fromMouvementRepository.findById(id);
    }

    @Override
    public List<FromMouvement> findAll() {
        return fromMouvementRepository.findAll();
    }



    @Override
    public void deleteById(Long id) {
        fromMouvementRepository.deleteById(id);
    }

    @Override
    public List<FromMouvement> getFromMouvementsByVehiculeRouteId(Long vehiculeRouteId) {
        return fromMouvementRepository.findByVehiculeRouteId(vehiculeRouteId);
    }
    @Override
    public List<FromMouvement> updateFromMouvement(FromMouvementRequestDTO fromMouvementRequestDTO, String token) throws IOException {

        if (fromMouvementRequestDTO.getId() == null) {
            throw new IllegalArgumentException("The provided ID must not be null");
        }

        FromMouvement existingFromMouvement = fromMouvementRepository.findById(fromMouvementRequestDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("FromMouvement not found"));

        // Ensure only one of Affaire or Fournisseur is set
        if (fromMouvementRequestDTO.getAffaireId() != null && fromMouvementRequestDTO.getFournisseurId() != null) {
            throw new IllegalArgumentException("FromMouvement cannot have both an Affaire and a Fournisseur at the same time");
        }

        // Handle Affaire assignment
        if (fromMouvementRequestDTO.getAffaireId() != null) {
            Affaire affaire = affaireService.getAffaireById(fromMouvementRequestDTO.getAffaireId(), token);
            if (affaire == null) {
                throw new EntityNotFoundException("Affaire not found");
            }
            existingFromMouvement.setAffaireId(affaire.getId());
            existingFromMouvement.setAffaireCode(affaire.getCode());

            existingFromMouvement.setFournisseurid(0L); // Set to default value when not used
            existingFromMouvement.setFournisseurName("");
        } else {
            existingFromMouvement.setAffaireId(0L); // Set to default value when not used
            existingFromMouvement.setAffaireCode("");
        }

        // Handle Fournisseur assignment
        if (fromMouvementRequestDTO.getFournisseurId() != null) {
            Fournisseur fournisseur = fournisseurRepository.findById(fromMouvementRequestDTO.getFournisseurId())
                    .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found"));
            existingFromMouvement.setFournisseurid(fournisseur.getId());
            existingFromMouvement.setFournisseurName(fournisseur.getIntituleFournisseur());

            existingFromMouvement.setAffaireId(0L); // Set to default value when not used
            existingFromMouvement.setAffaireCode("");
        } else {
            existingFromMouvement.setFournisseurid(0L); // Set to default value when not used
            existingFromMouvement.setFournisseurName("");
        }

        existingFromMouvement.setBl(fromMouvementRequestDTO.getBl());
        existingFromMouvement.setBlMontant(fromMouvementRequestDTO.getBlMontant());
        existingFromMouvement.setDateBl(fromMouvementRequestDTO.getDateBl());

        fromMouvementRepository.save(existingFromMouvement);

        return getFromMouvementsByVehiculeRouteId(existingFromMouvement.getVehiculeRoute().getId());
    }

    @Override
    public List<FromMouvement> saveFromMouvement(FromMouvementRequestDTO fromMouvementRequestDTO, String token) throws IOException {
        // Fetch the VehiculeRoute using the provided ID
        VehiculeRoute vehiculeRoute = vehiculeRouteRepository.findById(fromMouvementRequestDTO.getVehiculeRouteId())
                .orElseThrow(() -> new EntityNotFoundException("VehiculeRoute not found"));

        vehiculeRoute.getFromMouvements().clear();

        FromMouvement fromMouvement = new FromMouvement();

        if (fromMouvementRequestDTO.getAffaireId() != null && fromMouvementRequestDTO.getFournisseurId() != null) {
            throw new IllegalArgumentException("FromMouvement cannot have both an Affaire and a Fournisseur at the same time");
        }

        if (fromMouvementRequestDTO.getAffaireId() != null) {
            Affaire affaire = affaireService.getAffaireById(fromMouvementRequestDTO.getAffaireId(), token);
            if (affaire == null) {
                throw new EntityNotFoundException("Affaire not found");
            }
            fromMouvement.setAffaireId(affaire.getId());
            fromMouvement.setAffaireCode(affaire.getCode());

            // Reset Fournisseur fields
            fromMouvement.setFournisseurid(0L);
            fromMouvement.setFournisseurName("");
        }

        // Handle Fournisseur assignment
        if (fromMouvementRequestDTO.getFournisseurId() != null) {
            Fournisseur fournisseur = fournisseurRepository.findById(fromMouvementRequestDTO.getFournisseurId())
                    .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found"));
            fromMouvement.setFournisseurid(fournisseur.getId());
            fromMouvement.setFournisseurName(fournisseur.getIntituleFournisseur());

            // Reset Affaire fields
            fromMouvement.setAffaireId(0L);
            fromMouvement.setAffaireCode("");
        }

        // Set remaining fields from the DTO
        fromMouvement.setVehiculeGpsLocation(vehiculeRoute.getVehiculeGpsLocation());
        fromMouvement.setVehiculeRoute(vehiculeRoute);
        fromMouvement.setBl(fromMouvementRequestDTO.getBl());
        fromMouvement.setBlMontant(fromMouvementRequestDTO.getBlMontant());
        fromMouvement.setDateBl(fromMouvementRequestDTO.getDateBl());

        // Save the new FromMouvement and add it to the VehiculeRoute
        FromMouvement savedFromMouvement = fromMouvementRepository.save(fromMouvement);
        vehiculeRoute.addFromMouvement(savedFromMouvement);

        // Save VehiculeRoute to maintain association
        vehiculeRouteRepository.save(vehiculeRoute);

        // Return updated list of FromMouvement for the VehiculeRoute
        return getFromMouvementsByVehiculeRouteId(fromMouvementRequestDTO.getVehiculeRouteId());
    }

}
