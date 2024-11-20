package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.models.Marche;

import java.io.IOException;
import java.util.List;

public interface AffaireService {
    
   //  List<Affaire> getAllAffaire() ;
   List<Marche> getALLAffaire(String token) throws IOException;

    Marche getAffaireById(Long id, String token);

  //   List<Affaire> saveAffaireFromApi() throws IOException ;

   //  Affaire getAffaireById(Long id) ;

     //void deleteAffaire(Long id) ;
}
