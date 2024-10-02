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

    @Override
    public FromMouvement save(FromMouvement fromMouvement) {
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
                fromMouvement.setAffaire(dto.getAffaire());
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
            if (dto.getDepartement() != null) {
                fromMouvement.setDepartement(dto.getDepartement());
            }
            if (dto.getToAffaire() != null) {
                fromMouvement.setToAffaire(dto.getToAffaire());
            }

            // Save and return the updated entity
            return fromMouvementRepository.save(fromMouvement);
        } else {
            // Throw an exception if the entity with the given ID is not found
            throw new IOException("FromMouvement with id " + dto.getVehiculeRouteId() + " not found.");
        }
    }

}
