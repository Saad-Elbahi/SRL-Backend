package ma.srmanager.srmouvementv.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.FournisseurDTO;
import ma.srmanager.srmouvementv.model.Fournisseur;
import ma.srmanager.srmouvementv.services.FournisseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/fournisseurapi")
@Slf4j
@CrossOrigin("*")
public class FournisseurController {
    private final FournisseurService fournisseurService;

    @PostMapping("/createFournisseur")
    public ResponseEntity<FournisseurDTO> createFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
        FournisseurDTO createdFournisseur = fournisseurService.createFournisseur(fournisseurDTO);
        return new ResponseEntity<>(createdFournisseur, HttpStatus.CREATED);
    }

    @PutMapping("/updateFournisseur")
    public ResponseEntity<FournisseurDTO> updateFournisseur(@PathVariable Long id, @RequestBody FournisseurDTO fournisseurDTO) {
        FournisseurDTO updatedFournisseur = fournisseurService.updateFournisseur(id, fournisseurDTO);
        if (updatedFournisseur != null) {
            return new ResponseEntity<>(updatedFournisseur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/AllFournisseurs")
    public ResponseEntity<List<FournisseurDTO>> getAllFournisseurs() {
        List<FournisseurDTO> fournisseurs = fournisseurService.getAllFournisseurs();
        return new ResponseEntity<>(fournisseurs, HttpStatus.OK);
    }

    @GetMapping("/fournisseurById")
    public ResponseEntity<FournisseurDTO> getFournisseurById(@PathVariable Long id) {
        Optional<FournisseurDTO> fournisseur = fournisseurService.getFournisseurById(new FournisseurDTO() {{
            setId(id);
        }});
        return fournisseur.map(f -> new ResponseEntity<>(f, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleteFournisseur")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        FournisseurDTO fournisseurDTO = new FournisseurDTO();
        fournisseurDTO.setId(id);
        fournisseurService.deleteFournisseur(fournisseurDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/uploadFournisseur")
    public ResponseEntity<String> uploadFournisseurs(@RequestParam("file") MultipartFile file) {
        try {
            fournisseurService.uploadFournisseurs(file);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

   /* @GetMapping("/saveFournisseurFromApi")
    public List<Fournisseur> saveFournisseurFromApi() {
        try {
           return fournisseurService.saveFournisseurFromApi();
            //return "Fournisseurs saved successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
           // return "Failed to save Fournisseurs: " + e.getMessage();
        }
    }*/

}
