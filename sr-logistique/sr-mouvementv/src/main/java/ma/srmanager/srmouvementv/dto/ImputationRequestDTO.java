package ma.srmanager.srmouvementv.dto;

import lombok.Data;

import java.util.List;
@Data
public class ImputationRequestDTO {
    private Long vehiculeRouteId;
    private List<TripImputationDTO> imputations;
}
