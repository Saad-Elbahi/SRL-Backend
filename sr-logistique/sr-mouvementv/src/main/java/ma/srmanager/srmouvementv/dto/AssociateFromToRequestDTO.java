package ma.srmanager.srmouvementv.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssociateFromToRequestDTO {
    private Long vehiculeRouteId;
    private List<FromMouvementUpdateDTO> fromMouvements;
}
