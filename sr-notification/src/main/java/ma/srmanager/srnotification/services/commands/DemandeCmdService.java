package ma.srmanager.srnotification.services.commands;

import ma.srmanager.coreapi.notification.CreateDemandeRequestDTO;
import ma.srmanager.coreapi.notification.UpdateDemandeStatusDTO;
import ma.srmanager.srnotification.entities.Demande;

import java.util.List;

public interface DemandeCmdService {

    List<Demande> create(CreateDemandeRequestDTO dto);

    Demande updateReadStatus(UpdateDemandeStatusDTO dto);

}
