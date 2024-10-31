package ma.srmanager.srmouvementv.services;

import lombok.AllArgsConstructor;
import ma.srmanager.srmouvementv.dto.TripImputationDTO;
import ma.srmanager.srmouvementv.model.TripImputation;
import ma.srmanager.srmouvementv.repositories.TripImputationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TripImputationServiceImpl implements TripImputationService {

    private TripImputationRepository tripImputationRepository;

    @Override
    public TripImputation saveImputation(TripImputation imputation) {
        return tripImputationRepository.save(imputation);
    }

    @Override
    public List<TripImputation> getAllImputations() {
        return tripImputationRepository.findAll();
    }

    @Override
    public TripImputation getImputationById(Long id) {
        return tripImputationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteImputation(Long id) {
        tripImputationRepository.deleteById(id);
    }

    public TripImputation updateImputation(TripImputationDTO updateDTO) {
        TripImputation existingImputation = tripImputationRepository.findById(updateDTO.getId())
                .orElseThrow(() -> new RuntimeException("TripImputation not found with id: " + updateDTO.getId()));

        // Update the fields
        existingImputation.setFillingPercentage(updateDTO.getFillingPercentage());
        existingImputation.setObservation(updateDTO.getObservation());
        existingImputation.setAffaireId(updateDTO.getAffaireId());
        existingImputation.setSubContractorId(updateDTO.getSubContractorId());

        // Recalculate the cost imputation
        existingImputation.calculateCostImputation();

        // Save the updated imputation
        return tripImputationRepository.save(existingImputation);
    }
    @Override
    public List<TripImputation> geImputationByVehiculeRouteId(Long vehiculeRouteId) {
        return tripImputationRepository.findByVehiculeRouteId(vehiculeRouteId);
    }
//kpi


}
