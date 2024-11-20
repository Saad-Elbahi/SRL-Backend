package ma.srmanager.srmouvementv.controllers;

import lombok.AllArgsConstructor;
import ma.srmanager.srmouvementv.dto.TripImputationRequestDTO;
import ma.srmanager.srmouvementv.entities.TripImputation;
import ma.srmanager.srmouvementv.services.TripImputationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tripimpapi")
public class TripImputationController {

    private static final Logger log = LoggerFactory.getLogger(TripImputationController.class);
    private final TripImputationService tripImputationService;

    @GetMapping("/allImputation")
    public ResponseEntity<List<TripImputation>> getAllImputation() {
        List<TripImputation> imputations = tripImputationService.getAllImputations();
        return ResponseEntity.ok(imputations);
    }

    @GetMapping("/imputationByVehiculeRoute/{vehiculeRouteId}")
    public List<TripImputation> getimputationByVehiculeRouteId(@PathVariable Long vehiculeRouteId) {
        return tripImputationService.geImputationByVehiculeRouteId(vehiculeRouteId);
    }

    @DeleteMapping("/deleteImputation/{id}")
    public ResponseEntity<Void> deleteImputation(@PathVariable Long id) {
        tripImputationService.deleteImputation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/saveImputation")
    public ResponseEntity<List<TripImputation>> saveImputation(@RequestBody TripImputationRequestDTO dto,
                                                               @RequestHeader(name = "Authorization") String token) {
        try {

            List<TripImputation> tripImputations = tripImputationService.saveImputation(dto, token);

            return ResponseEntity.ok(tripImputations);
        } catch (EntityNotFoundException e) {
            log.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateImputation/{id}")
    public ResponseEntity<List<TripImputation>> updateImputation(@PathVariable Long id, @RequestBody TripImputationRequestDTO dto,
                                                                 @RequestHeader(name = "Authorization") String token) {
        dto.setId(id);
        try {

            List<TripImputation> tripImputations = tripImputationService.saveImputation(dto, token);

            return ResponseEntity.ok(tripImputations);
        } catch (EntityNotFoundException e) {
            log.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //kpi endpoint


}
