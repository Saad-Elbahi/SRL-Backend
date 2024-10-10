package ma.srmanager.srmouvementv.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.model.Affaire;
import ma.srmanager.srmouvementv.services.AffaireService;
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
    public List<Affaire> getProjets(@RequestHeader(name="Authorization") String token) throws IOException {
        log.info(token);
        return affaireService.getALLAffaire(token);
    }
}
