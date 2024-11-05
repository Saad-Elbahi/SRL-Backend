package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.TripImputationRequestDTO;
import ma.srmanager.srmouvementv.model.TripImputation;

import java.io.IOException;
import java.util.List;

public interface TripImputationService {

    TripImputation saveImputation(TripImputation imputation);

    List<TripImputation> getAllImputations();

    List<TripImputation> saveImputation(TripImputationRequestDTO dto, String token) throws IOException;

    List<TripImputation> updateImputation(TripImputationRequestDTO dto, String token);

    TripImputation getImputationById(Long id);

    void deleteImputation(Long id);

    List<TripImputation> geImputationByVehiculeRouteId(Long vehiculeRouteId);


}
