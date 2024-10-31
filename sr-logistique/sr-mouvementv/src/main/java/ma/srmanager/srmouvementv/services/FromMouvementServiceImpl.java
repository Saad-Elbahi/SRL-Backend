package ma.srmanager.srmouvementv.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.srmanager.srmouvementv.dto.FromMouvementUpdateDTO;
import ma.srmanager.srmouvementv.model.FromMouvement;
import ma.srmanager.srmouvementv.repositories.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FromMouvementServiceImpl implements FromMouvementService {

    private FromMouvementRepository fromMouvementRepository;
    private static final String fileUploadDir = System.getProperty("user.home") + "/Apps/sr-manager/RESSOURCES/data/assets/images/";

    @Override
    public FromMouvement save(FromMouvementUpdateDTO dto) throws IOException {
        // Create a new FromMouvement entity
        FromMouvement fromMouvement = new FromMouvement();

        // Set fields from DTO
        fromMouvement.setAffaireId(dto.getAffaire().getId());
        fromMouvement.setAffaireCode(dto.getAffaire().getCode());
        fromMouvement.setFournisseur(dto.getFournisseur());
        fromMouvement.setBl(dto.getBl());
        fromMouvement.setBlMontant(dto.getBlMontant());
        fromMouvement.setDateBl(dto.getDateBl());

        // Save and return the created FromMouvement entity
        return fromMouvementRepository.save(fromMouvement);
    }

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
    public FromMouvement updateFromMouvement(FromMouvementUpdateDTO dto) throws IOException {
        // Retrieve the existing FromMouvement by its ID
        Optional<FromMouvement> optionalFromMouvement = fromMouvementRepository.findById(dto.getVehiculeRouteId());

        if (optionalFromMouvement.isPresent()) {
            // Get the existing entity
            FromMouvement fromMouvement = optionalFromMouvement.get();

            // Update the fields if they are present in the DTO
            if (dto.getAffaire() != null) {
                fromMouvement.setAffaireId(dto.getAffaire().getId());
                fromMouvement.setAffaireCode(dto.getAffaire().getCode());
            }
            if (dto.getFournisseur() != null) {
                fromMouvement.setFournisseur(dto.getFournisseur());
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

            return fromMouvementRepository.save(fromMouvement);
        } else {
            // Throw an exception if the entity with the given ID is not found
            throw new IOException("FromMouvement with id " + dto.getVehiculeRouteId() + " not found.");
        }
    }

}
