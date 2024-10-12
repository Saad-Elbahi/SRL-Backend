package ma.srmanager.srmouvementv.controllers;

import ma.srmanager.srmouvementv.dto.LotDTO;
import ma.srmanager.srmouvementv.services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/lotApi")
public class LotController {
    @Autowired
    private LotService lotService;

    @PostMapping("/creatLot")
    public ResponseEntity<LotDTO> createLot(@RequestBody LotDTO lotDTO) {
        LotDTO createdLot = lotService.createLot(lotDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLot);
    }

    @PutMapping("/updateLot")
    public ResponseEntity<LotDTO> updateLot(@RequestBody LotDTO lotDTO) {
        LotDTO updatedLot = lotService.updateLot(lotDTO.getId(), lotDTO);
        if (updatedLot != null) {
            return ResponseEntity.ok(updatedLot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/allLots")
    public ResponseEntity<List<LotDTO>> getAllLots() {
        List<LotDTO> lots = lotService.getAllLots();
        return ResponseEntity.ok(lots);
    }

    @GetMapping("/lotById")
    public ResponseEntity<LotDTO> getLotById(@RequestBody LotDTO lotDTO) {
        return lotService.getLotById(lotDTO.getId())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteLot")
    public ResponseEntity<Void> deleteLot(@RequestBody LotDTO lotDTO) {
        lotService.deleteLot(lotDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
