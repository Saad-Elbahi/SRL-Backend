package ma.srmanager.srmouvementv.dto;

import lombok.Data;
import ma.srmanager.srmouvementv.model.TripImputation;

import java.util.List;
@Data
public class ImputationRequestDTO {
    private Long vehiculeRouteId;
    private List<TripImputation> imputations;
}
