package ma.srmanager.srmouvementv.dto;

import lombok.Data;
import ma.srmanager.srmouvementv.entities.FromMouvement;
import ma.srmanager.srmouvementv.entities.TripImputation;

import java.util.List;
@Data
public class AssociateRequestDTO {
    private List<FromMouvement> fromMouvements; // Changed to a list
    private Long toAffaireId;
    private List<TripImputation> imputations;
}
