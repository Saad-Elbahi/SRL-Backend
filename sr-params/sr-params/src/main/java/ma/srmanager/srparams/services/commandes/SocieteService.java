package ma.srmanager.srparams.services.commandes;

import ma.srmanager.coreapi.params.dtos.CreateSocieteDTO;
import ma.srmanager.srparams.entities.Societe;

public interface SocieteService {
    Societe createSociete(CreateSocieteDTO dto);
}
