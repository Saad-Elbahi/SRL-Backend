package ma.srmanager.srnotification.services.commands;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.notification.CreateDemandeRequestDTO;
import ma.srmanager.coreapi.notification.UpdateDemandeStatusDTO;
import ma.srmanager.srnotification.entities.Demande;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DemandeCmdServiceImpl implements DemandeCmdService {
    @Override
    public List<Demande> create(CreateDemandeRequestDTO dto) {
        return null;
    }

    @Override
    public Demande updateReadStatus(UpdateDemandeStatusDTO dto) {
        return null;
    }
}
