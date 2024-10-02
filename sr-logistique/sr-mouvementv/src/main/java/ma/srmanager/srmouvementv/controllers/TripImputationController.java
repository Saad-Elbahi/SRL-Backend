package ma.srmanager.srmouvementv.controllers;

import lombok.AllArgsConstructor;
import ma.srmanager.srmouvementv.model.TripImputation;
import ma.srmanager.srmouvementv.services.TripImputationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tripimpapi")
public class TripImputationController {

    private final TripImputationService tripImputationService;

    @GetMapping("/imputationByVehiculeRoute/{vehiculeRouteId}")
    public List<TripImputation> getimputationByVehiculeRouteId(@PathVariable Long vehiculeRouteId) {
        return tripImputationService.geImputationByVehiculeRouteId(vehiculeRouteId);
    }

    @DeleteMapping("/deleteImputation/{id}")
    public ResponseEntity<Void> deleteImputation (@PathVariable Long id) {
        tripImputationService.deleteImputation(id);
        return ResponseEntity.noContent().build();
    }
    //kpi endpoint


}
