package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.model.Fournisseur;
import java.io.IOException;
import java.util.List;

public interface FournisseurService {

     List<Fournisseur> getAllFrs();

     List<Fournisseur> saveFournisseurFromApi() throws IOException;

     Fournisseur getFournisseurById(Long fournisseurId);

     void deleteFournisseur(Long id) ;
}
