package ma.srmanager.srmouvementv.dto;

import lombok.Data;

@Data
public class AssociateChauffeurAndPriceDTO {
    private Long vehiculeId;
    private Long chauffeurId;
    private Double costPerKm;
    private String model;
    private String name;
    private String device;
    private String plateNumber;
}
