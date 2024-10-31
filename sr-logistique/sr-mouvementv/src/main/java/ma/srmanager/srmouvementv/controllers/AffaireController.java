package ma.srmanager.srmouvementv.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.AffaireDTO;
import ma.srmanager.srmouvementv.models.Affaire;
import ma.srmanager.srmouvementv.services.AffaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/affaireapi")
@AllArgsConstructor
@Slf4j
public class AffaireController {

    private AffaireService affaireService;

    /*
        @GetMapping("/getAllAffaire")
        public List<Affaire> getAllAffaire() {
            return affaireService.getAllAffaire();
        }

        @GetMapping("/saveAffaireFromApi")
        public List<Affaire> saveAffaireFromApi() {
            try {
                return affaireService.saveAffaireFromApi();
                //return "Affaires saved successfully";
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @GetMapping("/getAffaireById/{id}")
        public Affaire getAffaireById(@PathVariable Long id) {
            return affaireService.getAffaireById(id);
        }

        @DeleteMapping("/deleteAffaire/{id}")
        public void deleteAffaire(@PathVariable Long id) {
            affaireService.deleteAffaire(id);
        }*/
    @GetMapping("/affaires")
    public List<Affaire> getAffaire(@RequestHeader(name = "Authorization") String token) throws IOException {
        log.info(token);
        return affaireService.getALLAffaire(token);
    }
   /* @PutMapping("/updateAffaire")
    public ResponseEntity<Void> updateAffaire(@RequestBody AffaireDTO affaireDTO) throws IOException {
        try {
            affaireService.UpdateAffaire(affaireDTO);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            log.error("Invalid format: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
*/
}
