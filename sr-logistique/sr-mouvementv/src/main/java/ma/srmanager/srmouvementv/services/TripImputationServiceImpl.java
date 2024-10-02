package ma.srmanager.srmouvementv.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.srmanager.srmouvementv.model.TripImputation;
import ma.srmanager.srmouvementv.repositories.AffaireRepository;
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

    @Override
    public List<TripImputation> geImputationByVehiculeRouteId(Long vehiculeRouteId) {
        return tripImputationRepository.findByVehiculeRouteId(vehiculeRouteId);
    }
//kpi


}
