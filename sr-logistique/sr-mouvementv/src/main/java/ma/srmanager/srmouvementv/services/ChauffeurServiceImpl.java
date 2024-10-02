package ma.srmanager.srmouvementv.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.model.Chauffeur;
import ma.srmanager.srmouvementv.model.Fournisseur;
import ma.srmanager.srmouvementv.repositories.ChauffeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChauffeurServiceImpl implements ChauffeurService {


    @Autowired
    private ChauffeurRepository chauffeurRepository;

    private static final  String fileUploadDir = System.getProperty("user.home") + "/Apps/sr-manager/RESSOURCES/data/assets/images/";


    @Override
    public List<Chauffeur> saveChauffeur(Chauffeur chauffeur, MultipartFile file) throws IOException {
        log.info("saveChauffeur 1");

        try {
            if (!file.isEmpty()) {
                log.info("saveChauffeur 2");
                log.info(fileUploadDir);

                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(fileUploadDir + "avatars/" + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());
                chauffeur.setImgPath("/assets/images/avatars/" + fileName);
            }
            chauffeurRepository.save(chauffeur);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return chauffeurRepository.findAll();
    }

    @Override
    public List<Chauffeur> getAllChauffeurs() {
        log.info("get All Chauffeurs");

        List<Chauffeur> chauffeurs = new ArrayList<>();
        try {
            chauffeurs = chauffeurRepository.findAll();
            log.info("size Of Chauffeurs ");
            log.info(String.valueOf(chauffeurs.size()));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return chauffeurs;
    }

    @Override
    public Chauffeur getChauffeurById(Long chauffeurId) {
        return chauffeurRepository.findById(chauffeurId)
                .orElseThrow(() -> new RuntimeException("Chauffeur not found with id: " + chauffeurId));
    }

    @Override
    public List<Chauffeur> updateChauffeur(Long id, Chauffeur chauffeurDetails, MultipartFile file) throws IOException {
        log.info("updateChauffeur id=>" + chauffeurDetails.getId());
        log.info("uploadDir=>" + fileUploadDir);
        try {
            Optional<Chauffeur> optionalChauffeur = chauffeurRepository.findById(id);
            if (optionalChauffeur.isPresent()) {
                Chauffeur chauffeur = optionalChauffeur.get();
                chauffeur.setName(chauffeurDetails.getName());
                chauffeur.setIdn(chauffeurDetails.getIdn());
                chauffeur.setAddress(chauffeurDetails.getAddress());
                chauffeur.setPhone(chauffeurDetails.getPhone());
                chauffeur.setEmail(chauffeurDetails.getEmail());

                if (file != null && !file.isEmpty()) {
                    // Handle file upload
                    String fileName = file.getOriginalFilename();
                    String filePath = fileUploadDir + "avatars/" + fileName;
                    Path path = Paths.get(filePath);

                    // Ensure the directory exists
                    Files.createDirectories(path.getParent());

                    // Write file to the path
                    Files.write(path, file.getBytes());

                    // Set the relative path for the Chauffeur object
                    chauffeur.setImgPath("/assets/images/avatars/" + fileName);
                }

                chauffeurRepository.save(chauffeur);
            } else {
                throw new RuntimeException("Chauffeur not found with id: " + id);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
       return chauffeurRepository.findAll();
    }

    @Override
    public void deleteChauffeur(Long id) {
        chauffeurRepository.deleteById(id);
    }
}
