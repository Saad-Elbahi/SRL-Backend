package ma.srmanager.srmouvementv.dto;

import lombok.Data;


@Data
public class TripImputationDTO {
    private Long id;
    private Long affaireId;
    private Double fillingPercentage;
    private String observation;
    private Long clientId;
    private Long lotId;
    private Long soustraitantId;
    private Double costImputation;
}
