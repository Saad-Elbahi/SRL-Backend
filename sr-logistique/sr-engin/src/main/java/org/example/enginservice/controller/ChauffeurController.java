package org.example.enginservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.enginservice.entities.Chauffeur;
import org.example.enginservice.service.ChauffeurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/chauffeurEnginapi")
@Slf4j
@AllArgsConstructor
public class ChauffeurController {

    private ChauffeurService chauffeurService;


    @GetMapping("/getAllChauffeurs")
    public List<Chauffeur> getAllChauffeurs() {
        return chauffeurService.getAllChauffeurs();
    }

    @GetMapping("/getChauffeurById/{id}")
    public Chauffeur getChauffeurById(@PathVariable Long id) {
        return chauffeurService.getChauffeurById(id);
    }

    @PostMapping("/createChauffeur")
    public List<Chauffeur> createChauffeur(@RequestPart("chauffeur") Chauffeur chauffeur,
                                                     @RequestPart("file") MultipartFile file) {

        log.info("createChauffeur 1");
        try {
           return chauffeurService.saveChauffeur(chauffeur, file);
            //return new ResponseEntity<>(savedChauffeur, HttpStatus.CREATED);
        } catch (IOException e) {
            return chauffeurService.getAllChauffeurs();
        }
    }

    @PutMapping("/updateChauffeur/{id}")
    public List<Chauffeur> updateChauffeur(
            @PathVariable Long id,
            @RequestPart("chauffeur") Chauffeur chauffeurDetails,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            log.info("updateChauffeur id=>"+chauffeurDetails.getId());

          return chauffeurService.updateChauffeur(id, chauffeurDetails, file);
        } catch (IOException e) {
            return chauffeurService.getAllChauffeurs();
        }
    }

    @DeleteMapping("/deleteChauffeur/{id}")
    public ResponseEntity<Void> deleteChauffeur(@PathVariable Long id) {
        try {
            chauffeurService.deleteChauffeur(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
