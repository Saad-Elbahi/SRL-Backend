package ma.srmanager.srmouvementv.dto;

import lombok.Data;
import ma.srmanager.srmouvementv.model.FromMouvement;
import ma.srmanager.srmouvementv.model.TripImputation;

import java.util.List;
@Data
public class AssociateRequestDTO {
    private List<FromMouvement> fromMouvements; // Changed to a list
    private Long toAffaireId;
    private List<TripImputation> imputations;
}
