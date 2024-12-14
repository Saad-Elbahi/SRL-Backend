package org.example.enginservice.dto;

import lombok.Data;

@Data
public class EnginImputationRequestDTO {
    private Long id;
    private Long enginRouteId;
    private Long affaireId;
    private String affaireCode;
    private Double nbrHImputation;
    private String observation;
    private Long clientId;
    private Long lotId;
    private Long subContractorId;
    private String subContractorFullName;
    private Double costImputation;
}
