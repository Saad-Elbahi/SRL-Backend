package ma.srmanager.srmouvementv.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.model.Fournisseur;
import ma.srmanager.srmouvementv.services.FournisseurService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/fournisseurapi")
@Slf4j
public class FournisseurController {
    private final FournisseurService fournisseurService;

    @GetMapping("/getAllFournisseur")
    public List<Fournisseur> getAllAffaire() {
        log.info("getAllFournisseur");
        return fournisseurService.getAllFrs();
    }

    @GetMapping("/saveFournisseurFromApi")
    public List<Fournisseur> saveFournisseurFromApi() {
        try {
           return fournisseurService.saveFournisseurFromApi();
            //return "Fournisseurs saved successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
           // return "Failed to save Fournisseurs: " + e.getMessage();
        }
    }

    @GetMapping("/geFournisseurById/{id}")
    public Fournisseur geFournisseurById(@PathVariable Long id) {
        return fournisseurService.getFournisseurById(id);
    }

    @DeleteMapping("/deleteFournisseur/{id}")
    public void deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
    }
}
