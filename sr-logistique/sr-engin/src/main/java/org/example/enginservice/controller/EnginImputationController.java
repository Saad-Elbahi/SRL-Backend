package org.example.enginservice.controller;

import lombok.AllArgsConstructor;

import org.example.enginservice.dto.EnginImputationRequestDTO;
import org.example.enginservice.entities.EnginImputation;
import org.example.enginservice.service.EnginImputationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Enginimpapi")
public class EnginImputationController {
    private final EnginImputationService enginImputationService;
    private static final Logger log = LoggerFactory.getLogger(EnginImputationController.class);

    @GetMapping("/allImputation")
    public ResponseEntity<List<EnginImputation>> getAllImputation() {
        List<EnginImputation> imputations = enginImputationService.getAllImputations();
        return ResponseEntity.ok(imputations);
    }

    @GetMapping("/imputationByEnginRoute/{enginRouteId}")
    public List<EnginImputation> getimputationByVehiculeRouteId(@PathVariable Long enginRouteId) {
        return enginImputationService.geImputationByEnginRouteId(enginRouteId);
    }

    @DeleteMapping("/deleteImputation/{id}")
    public ResponseEntity<Void> deleteImputation(@PathVariable Long id) {
        enginImputationService.deleteImputation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/saveImputationEngin")
    public ResponseEntity<List<EnginImputation>> saveImputation(@RequestBody EnginImputationRequestDTO dto,
                                                                @RequestHeader(name = "Authorization") String token) {
        try {

            List<EnginImputation> enginImputations = enginImputationService.saveImputation(dto, token);

            return ResponseEntity.ok(enginImputations);
        } catch (EntityNotFoundException e) {
            log.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateImputation/{id}")
    public ResponseEntity<List<EnginImputation>> updateImputation(@PathVariable Long id, @RequestBody EnginImputationRequestDTO dto,
                                                                  @RequestHeader(name = "Authorization") String token) {
        dto.setId(id);
        try {

            List<EnginImputation> enginImputations = enginImputationService.saveImputation(dto, token);

            return ResponseEntity.ok(enginImputations);
        } catch (EntityNotFoundException e) {
            log.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
