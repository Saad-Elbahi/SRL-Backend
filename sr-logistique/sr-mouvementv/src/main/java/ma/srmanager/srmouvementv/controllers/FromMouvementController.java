package ma.srmanager.srmouvementv.controllers;

import lombok.AllArgsConstructor;
import ma.srmanager.srmouvementv.dto.FromMouvementUpdateDTO;
import ma.srmanager.srmouvementv.model.FromMouvement;
import ma.srmanager.srmouvementv.services.FromMouvementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/frommvtapi")
public class FromMouvementController {

    private final FromMouvementService fromMouvementService;

    //Utiliserun DTO
    @PostMapping
    public ResponseEntity<FromMouvement> createFromMouvement(@RequestBody FromMouvement fromMouvement) {
        FromMouvement savedFromMouvement = fromMouvementService.save(fromMouvement);
        return ResponseEntity.ok(savedFromMouvement);
    }

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

    @PutMapping("/updateFrom")
    public ResponseEntity<FromMouvement> updateFromMouvement(@RequestBody FromMouvementUpdateDTO dto) {
        try {
            FromMouvement updatedEntity = fromMouvementService.updateFromMouvement(dto);
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
