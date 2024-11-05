package ma.srmanager.srmouvementv.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.FromMouvementRequestDTO;
import ma.srmanager.srmouvementv.dto.FromMouvementUpdateDTO;
import ma.srmanager.srmouvementv.model.Fournisseur;
import ma.srmanager.srmouvementv.model.FromMouvement;
import ma.srmanager.srmouvementv.model.VehiculeRoute;
import ma.srmanager.srmouvementv.models.Affaire;
import ma.srmanager.srmouvementv.repositories.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    public List<FromMouvement> updateFromMouvement(FromMouvementRequestDTO dto) throws IOException {
        // Retrieve the existing FromMouvement by its ID
        Optional<FromMouvement> optionalFromMouvement = fromMouvementRepository.findById(dto.getVehiculeRouteId());

        if (optionalFromMouvement.isPresent()) {
            // Get the existing entity
            FromMouvement fromMouvement = optionalFromMouvement.get();

            // Update fields if they are present in the DTO
            if (dto.getAffaireId() != null) {
                fromMouvement.setAffaireId(dto.getAffaireId());
            }
            if (dto.getAffaireCode() != null) {
                fromMouvement.setAffaireCode(dto.getAffaireCode());
            }
            if (dto.getFournisseurId() != null) {
                fromMouvement.setFournisseurid(dto.getFournisseurId());
            }
            if (dto.getFournisseurName() != null) {
                fromMouvement.setFournisseurName(dto.getFournisseurName());
            }
            if (dto.getBl() != null) {
                fromMouvement.setBl(dto.getBl());
            }
            if (dto.getBlMontant() != null) {
                fromMouvement.setBlMontant(dto.getBlMontant());
            }
            if (dto.getDateBl() != null) {
                fromMouvement.setDateBl(dto.getDateBl());
            }

            // Save the updated entity and return it
             fromMouvementRepository.save(fromMouvement);
            return getFromMouvementsByVehiculeRouteId(dto.getVehiculeRouteId());
        } else {
            // Throw an exception if the entity with the given ID is not found
            throw new IOException("FromMouvement with id " + dto.getVehiculeRouteId() + " not found.");
        }
    }

    @Override
    public List<FromMouvement> saveFromMouvement(FromMouvementRequestDTO fromMouvementRequestDTO, String token) throws IOException {
        // Fetch the VehiculeRoute using the provided ID
        VehiculeRoute vehiculeRoute = vehiculeRouteRepository.findById(fromMouvementRequestDTO.getVehiculeRouteId())
                .orElseThrow(() -> new EntityNotFoundException("VehiculeRoute not found"));

        // Clear existing FromMouvement list if any
        vehiculeRoute.getFromMouvements().clear();

        // Create a new FromMouvement instance
        FromMouvement fromMouvement = new FromMouvement();

        // Check for Affaire and Fournisseur conflict
        if (fromMouvementRequestDTO.getAffaireId() != null && fromMouvementRequestDTO.getFournisseurId() != null) {
            throw new IllegalArgumentException("FromMouvement cannot have both an Affaire and a Fournisseur at the same time");
        }

        // Handle Affaire assignment
        if (fromMouvementRequestDTO.getAffaireId() != null) {
            Affaire affaire = affaireService.getAffaireById(fromMouvementRequestDTO.getAffaireId(), token);
            if (affaire == null) {
                log.info("Affaire not found");
                throw new EntityNotFoundException("Affaire not found");
            }
            fromMouvement.setAffaireId(affaire.getId());
            fromMouvement.setAffaireCode(affaire.getCode());
        }

        // Handle Fournisseur assignment
        if (fromMouvementRequestDTO.getFournisseurId() != null) {
            Fournisseur fournisseur = fournisseurRepository.findById(fromMouvementRequestDTO.getFournisseurId())
                    .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found"));
            fromMouvement.setFournisseurid(fournisseur.getId());
            fromMouvement.setFournisseurName(fournisseur.getIntituleFournisseur());

        }

        // Set other fields from the DTO
        fromMouvement.setVehiculeGpsLocation(vehiculeRoute.getVehiculeGpsLocation());
        fromMouvement.setVehiculeRoute(vehiculeRoute);
        fromMouvement.setBl(fromMouvementRequestDTO.getBl());
        fromMouvement.setBlMontant(fromMouvementRequestDTO.getBlMontant());
        fromMouvement.setDateBl(fromMouvementRequestDTO.getDateBl());

        // Save the FromMouvement and add it to the VehiculeRoute
        FromMouvement savedFromMouvement = fromMouvementRepository.save(fromMouvement);
        vehiculeRoute.addFromMouvement(savedFromMouvement);

        // Save and return the updated list of FromMouvements
        vehiculeRouteRepository.save(vehiculeRoute);
        return getFromMouvementsByVehiculeRouteId(fromMouvementRequestDTO.getVehiculeRouteId());
    }


}
