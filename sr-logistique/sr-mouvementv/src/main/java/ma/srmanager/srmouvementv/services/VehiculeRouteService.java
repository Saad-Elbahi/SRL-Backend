package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.PerformanceOverTimeRequestDTO;
import ma.srmanager.srmouvementv.dto.UpdateFillingPercentageDTO;
import ma.srmanager.srmouvementv.model.*;
import java.time.LocalDate;
import java.util.*;

public interface VehiculeRouteService {

     List<VehiculeRoute> getAllVehiculeRoutes() ;

     VehiculeRoute getVehiculeRouteById(Long id) ;

     void fetchAndSaveAllObjectRoutes(LocalDate startDate, LocalDate endDate);

     void fetchAndSaveRouteForVehicle(VehiculeGpsLocation vehicle, LocalDate date);

     VehiculeRoute associateFromMouvementsAndTo(Long vehiculeRouteId, List<FromMouvement> fromMouvements) ;

    //service Association Imputation

     VehiculeRoute associateImputation(Long vehiculeRouteId, List<TripImputation> imputations);

     void deleteVehiculeroute(Long id);

     List<Map<String, Object>> getTotalRouteLengthByVehicule();

     List<VehiculeRoute> getPerformanceData(LocalDate startDate, LocalDate endDate) ;

     Map<LocalDate, Double> getPerformanceDataAsMap(LocalDate startDate, LocalDate endDate);

     List<Object[]> getTotalCostPerTripByMonth();

     Double getTotalCostForSpecificMonth(int year, int month) ;

     Map<Affaire, Double> calculateCostPerAffaire() ;

    //cost by group
     Map<String, Object> getTotalCostByGroupName() ;


    //--------------------------------costfiiling
     List<VehiculeRoute> updateFillingPercentage(UpdateFillingPercentageDTO dto);

    Map<LocalDate, Double> performanceOverTime(PerformanceOverTimeRequestDTO dto);


}






