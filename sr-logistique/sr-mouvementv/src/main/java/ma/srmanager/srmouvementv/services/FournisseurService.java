package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.FournisseurDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FournisseurService {

     FournisseurDTO createFournisseur(FournisseurDTO fournisseurDTO);
     FournisseurDTO updateFournisseur(Long id, FournisseurDTO fournisseurDTO);
     List<FournisseurDTO> getAllFournisseurs();
     Optional<FournisseurDTO> getFournisseurById(FournisseurDTO fournisseurDTO);
     void deleteFournisseur(FournisseurDTO fournisseurDTO);
      void uploadFournisseurs(MultipartFile file) throws IOException;
}
