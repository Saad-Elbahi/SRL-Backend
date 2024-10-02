package ma.srmanager.srmouvementv.dto;

import lombok.Data;

@Data
public class UpdateFillingPercentageDTO {
    private Long vehiculeRouteId;
    private double FillingPercentage;
}
