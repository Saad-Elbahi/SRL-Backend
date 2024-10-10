package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.AffaireDTO;
import ma.srmanager.srmouvementv.model.Affaire;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface AffaireService {
    
   //  List<Affaire> getAllAffaire() ;
     List<Affaire> getALLAffaire(String token) throws IOException;
     Affaire UpdateAffaire(AffaireDTO affaireDTO);

  //   List<Affaire> saveAffaireFromApi() throws IOException ;

   //  Affaire getAffaireById(Long id) ;

     //void deleteAffaire(Long id) ;
}
