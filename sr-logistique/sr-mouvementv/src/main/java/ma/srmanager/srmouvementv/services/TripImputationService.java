package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.model.TripImputation;
import java.util.List;

public interface TripImputationService {

     TripImputation saveImputation(TripImputation imputation);

     List<TripImputation> getAllImputations();

     TripImputation getImputationById(Long id);

     void deleteImputation(Long id);

     List<TripImputation> geImputationByVehiculeRouteId(Long vehiculeRouteId);


}
