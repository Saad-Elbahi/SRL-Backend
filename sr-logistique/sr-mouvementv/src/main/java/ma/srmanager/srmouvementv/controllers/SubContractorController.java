package ma.srmanager.srmouvementv.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.SoustraitantDTO;
import ma.srmanager.srmouvementv.models.SubContractor;
import ma.srmanager.srmouvementv.services.SubContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/soustraitantapi")
public class SubContractorController {
    @Autowired
    private SubContractorService subContractorService;

      @GetMapping("/soustraitants/{projectId}")
    public ResponseEntity<List<SubContractor>> getSoustraitants(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable Long projectId) {

        log.info("Authorization Token: {}", token);
        log.info("Project ID: {}", projectId);

        try {
            List<SubContractor> subcontractors = subContractorService.getAllSoustraitants(token, projectId);

            if (!subcontractors.isEmpty()) {
                return ResponseEntity.ok(subcontractors);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error retrieving subcontractors for project ID: {}", projectId, e);
            return ResponseEntity.status(500).build();
        }
    }



    /*@GetMapping("/allSoustraitant")
    public ResponseEntity<List<SoustraitantDTO>> getAllSoustraitant() {
        List<SoustraitantDTO> clients = subContractorService.getAllSoustraitant();
        return ResponseEntity.ok(clients);
    }*/

}
