package ma.srmanager.srmouvementv.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.*;
import ma.srmanager.srmouvementv.model.*;
import ma.srmanager.srmouvementv.repositories.TripImputationRepository;
import ma.srmanager.srmouvementv.services.VehiculeRouteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vhrouteapi")
@AllArgsConstructor
@Slf4j

public class VehiculeRouteController {


    private VehiculeRouteService vehiculeRouteService;
    private TripImputationRepository tripImputationRepository;


    @GetMapping("/getAllVehiculeRoutes")
    public List<VehiculeRoute> getAllVehiculeRoutes() {
        log.info("getAllVehiculeRoutes");
        return vehiculeRouteService.getAllVehiculeRoutes();
    }

    @GetMapping("/getVehiculeRouteById/{id}")
    public VehiculeRoute getVehiculeMouvementById(@PathVariable Long id) {
        return vehiculeRouteService.getVehiculeRouteById(id);
    }
/*

    @PostMapping("/associateFromTo")
    public ResponseEntity<VehiculeRoute> associateFromMouvementsAndTo(@RequestBody AssociateFromToRequestDTO request,
                                                                      @RequestHeader(name = "Authorization") String token) throws IOException {
        VehiculeRoute vehiculeRoute = vehiculeRouteService.associateFromMouvementsAndTo(request.getVehiculeRouteId(), request.getFromMouvements(), token);
        return ResponseEntity.ok(vehiculeRoute);
    }
*/







    //-----------------------------------------------------
    @GetMapping("/fetchAndSaveYesterdayRoutes")
    public ResponseEntity<String> fetchAndSaveYesterdayRoutes() {

        LocalDate yesterday = LocalDate.now().minusDays(1);

        try {
            vehiculeRouteService.fetchAndSaveAllObjectRoutes(yesterday, yesterday);
            return ResponseEntity.ok("Routes fetched and saved successfully for yesterday");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/fetchAndSaveTodayRoutes")
    public ResponseEntity<String> fetchAndSaveTodayRoutes() {

        LocalDate date = LocalDate.now();

        try {
            vehiculeRouteService.fetchAndSaveAllObjectRoutes(date, date);
            return ResponseEntity.ok("Routes fetched and saved successfully for To day");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/updateMouvement")
    public ResponseEntity<List<VehiculeRoute>> updateMouvement(@RequestBody UpdateMouvementDTO dto) {
        vehiculeRouteService.updateMouvement(dto);  // Update the specific route
        List<VehiculeRoute> allRoutes = vehiculeRouteService.getAllVehiculeRoutes();
        return ResponseEntity.ok(allRoutes);
    }


    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleFetchAndSaveYesterdayRoutes() {
        fetchAndSaveYesterdayRoutes();
        System.out.println("Routes fetched and saved successfully for yesterday (scheduled task)");
    }

    @DeleteMapping("/deleteVehiculeroute/{id}")
    public void deleteMouvement(@PathVariable Long id) {
        vehiculeRouteService.deleteVehiculeroute(id);
    }

    @GetMapping("/totalRouteLength")
    public List<Map<String, Object>> getTotalRouteLengthByVehicule() {
        return vehiculeRouteService.getTotalRouteLengthByVehicule();
    }

   /* @GetMapping("/performanceOverTime")
    public Map<LocalDate, Double> getPerformanceOverTime(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return vehiculeRouteService.getPerformanceDataAsMap(startDate, endDate);
    }*/

    @PostMapping("/performanceOverTime")
    public Map<LocalDate, Double> performanceOverTime(@RequestBody PerformanceOverTimeRequestDTO dto) {
        return vehiculeRouteService.performanceOverTime(dto);
    }

    @GetMapping("/totalCostPerTripByMonth/{year}/{month}")
    public Double getTotalCostForSpecificMonth(@PathVariable int year, @PathVariable int month) {
        return vehiculeRouteService.getTotalCostForSpecificMonth(year, month);
    }

    @GetMapping("/costPerAffaire")
    public ResponseEntity<Map<String, Double>> getCostPerAffaire() {
        List<TripImputation> imputations = tripImputationRepository.findAll();

        Map<String, Double> costPerAffaire = imputations.stream()
                .collect(Collectors.groupingBy(
                        TripImputation::getAffaireCode, // Assuming Affaire has a getName() method
                        Collectors.summingDouble(TripImputation::getCostImputation)
                ));

        return ResponseEntity.ok(costPerAffaire);
    }


    //cost by group
    @GetMapping("/costByGroup")
    public ResponseEntity<Map<String, Object>> getCostByGroupName() {
        Map<String, Object> result = vehiculeRouteService.getTotalCostByGroupName();
        return ResponseEntity.ok(result);
    }

    //costfilling
    @PutMapping("/updateFillingPercentage")
    public List<VehiculeRoute> updateFillingPercentage(@RequestBody UpdateFillingPercentageDTO dto) {
        log.info("updateFillingPercentage ==> 1");
        log.info("Vh ==> " + dto.getVehiculeRouteId());
        log.info("% ==> " + dto.getFillingPercentage());
        return vehiculeRouteService.updateFillingPercentage(dto);
        //return ResponseEntity.ok(updatedRoute);
    }

}
