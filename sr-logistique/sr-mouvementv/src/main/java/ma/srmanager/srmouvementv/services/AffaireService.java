package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.model.Affaire;
import java.io.IOException;
import java.util.List;

public interface AffaireService {
    
     List<Affaire> getAllAffaire() ;
     
     List<Affaire> saveAffaireFromApi() throws IOException ;

     Affaire getAffaireById(Long id) ;

     void deleteAffaire(Long id) ;
}
