package ma.srmanager.srmouvementv.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FromMouvementRequestDTO {
    private Long id;
    private Long vehiculeRouteId;
    private Long affaireId;
    private String affaireCode;
    private Long fournisseurId ;
    private String fournisseurName;
    private String bl;
    private Double blMontant;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateBl;
}
