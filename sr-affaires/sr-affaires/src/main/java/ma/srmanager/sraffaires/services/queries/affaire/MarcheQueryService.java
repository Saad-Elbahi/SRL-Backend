package ma.srmanager.sraffaires.services.queries.affaire;

import ma.srmanager.coreapi.enums.marche.ProjetStatus;
import ma.srmanager.sraffaires.entities.imputation.Marche;

import java.util.List;
import java.util.Optional;

public interface MarcheQueryService {

    List<Marche> byStatus(ProjetStatus projetStatus,String token);

    Marche marcheById(Long id);

    Optional<Marche> marcheByCode(String code);
    
    

}
