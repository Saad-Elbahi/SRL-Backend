package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.PerformanceOverTimeRequestDTO;
import ma.srmanager.srmouvementv.dto.UpdateFillingPercentageDTO;
import ma.srmanager.srmouvementv.dto.UpdateMouvementDTO;
import ma.srmanager.srmouvementv.entities.VehiculeGpsLocation;
import ma.srmanager.srmouvementv.entities.VehiculeRoute;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface VehiculeRouteService {

     List<VehiculeRoute> getAllVehiculeRoutes() ;

     VehiculeRoute getVehiculeRouteById(Long id) ;

     void fetchAndSaveAllObjectRoutes(LocalDate startDate, LocalDate endDate);

     void fetchAndSaveRouteForVehicle(VehiculeGpsLocation vehicle, LocalDate date);
    VehiculeRoute updateMouvement(UpdateMouvementDTO dto);

 //   VehiculeRoute associateFromMouvementsAndTo(Long vehiculeRouteId, List<FromMouvementUpdateDTO> fromMouvements,String token) throws IOException;

    //service Association Imputation


     void deleteVehiculeroute(Long id);

     List<Map<String, Object>> getTotalRouteLengthByVehicule();

     List<VehiculeRoute> getPerformanceData(LocalDate startDate, LocalDate endDate) ;

     Map<LocalDate, Double> getPerformanceDataAsMap(LocalDate startDate, LocalDate endDate);

     List<Object[]> getTotalCostPerTripByMonth();

     Double getTotalCostForSpecificMonth(int year, int month) ;

    Map<String, Double> calculateCostPerAffaire();

    //cost by group
     Map<String, Object> getTotalCostByGroupName() ;


    //--------------------------------costfiiling
     List<VehiculeRoute> updateFillingPercentage(UpdateFillingPercentageDTO dto);

    Map<LocalDate, Double> performanceOverTime(PerformanceOverTimeRequestDTO dto);

     List<VehiculeRoute> getVehiculeRoutesWithStatus();



}






