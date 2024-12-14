package org.example.enginservice.service;


import org.example.enginservice.entities.Chauffeur;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ChauffeurService {

     List<Chauffeur> saveChauffeur(Chauffeur chauffeur, MultipartFile file) throws IOException;

     List<Chauffeur> getAllChauffeurs();

     Chauffeur getChauffeurById(Long chauffeurId);

     List<Chauffeur> updateChauffeur(Long id, Chauffeur chauffeurDetails, MultipartFile file) throws IOException;

     void deleteChauffeur(Long id);
}
