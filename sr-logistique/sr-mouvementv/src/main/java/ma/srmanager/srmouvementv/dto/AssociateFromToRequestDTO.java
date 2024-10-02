package ma.srmanager.srmouvementv.dto;

import lombok.Data;
import ma.srmanager.srmouvementv.model.FromMouvement;

import java.util.List;

@Data
public class AssociateFromToRequestDTO {
    private Long vehiculeRouteId;
    private List<FromMouvement> fromMouvements;
}
