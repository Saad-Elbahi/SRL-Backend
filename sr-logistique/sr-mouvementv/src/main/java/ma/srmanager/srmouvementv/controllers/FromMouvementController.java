package ma.srmanager.srmouvementv.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.FromMouvementRequestDTO;
import ma.srmanager.srmouvementv.entities.FromMouvement;
import ma.srmanager.srmouvementv.services.FromMouvementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/frommvtapi")
public class FromMouvementController {

    private final FromMouvementService fromMouvementService;

   /* @PostMapping("/create")
    public ResponseEntity<FromMouvement> createFromMouvement(@RequestBody FromMouvementUpdateDTO dto) {
        try {
            FromMouvement savedFromMouvement = fromMouvementService.save(dto);
            return new ResponseEntity<>(savedFromMouvement, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/FromMouvement/{id}")
    public ResponseEntity<FromMouvement> getFromMouvementById(@PathVariable Long id) {
        Optional<FromMouvement> fromMouvement = fromMouvementService.findById(id);
        return fromMouvement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/allFrom")
    public ResponseEntity<List<FromMouvement>> getAllFromMouvements() {
        List<FromMouvement> fromMouvements = fromMouvementService.findAll();
        return ResponseEntity.ok(fromMouvements);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFromMouvement(@PathVariable Long id) {
        fromMouvementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byVehiculeRoute/{vehiculeRouteId}")
    public List<FromMouvement> getFromMouvementsByVehiculeRouteId(@PathVariable Long vehiculeRouteId) {
        return fromMouvementService.getFromMouvementsByVehiculeRouteId(vehiculeRouteId);
    }

    @PostMapping("/saveFromMouvement")
    public ResponseEntity<List<FromMouvement>> saveFromMouvement(@RequestBody FromMouvementRequestDTO fromMouvementRequestDTO,
                                                              @RequestHeader(name = "Authorization") String token) {
        try {

            List<FromMouvement> fromMouvements = fromMouvementService.saveFromMouvement(fromMouvementRequestDTO, token);

            return ResponseEntity.ok(fromMouvements);
        } catch (EntityNotFoundException e) {
            log.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/updateMouvement")
    public ResponseEntity<List<FromMouvement>> updateFromMouvement(@RequestBody FromMouvementRequestDTO fromMouvementRequestDTO,
                                                                   @RequestHeader("Authorization") String token) {
        try {
            List<FromMouvement> updatedFromMouvements = fromMouvementService.updateFromMouvement(fromMouvementRequestDTO, token);
            return new ResponseEntity<>(updatedFromMouvements, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}
