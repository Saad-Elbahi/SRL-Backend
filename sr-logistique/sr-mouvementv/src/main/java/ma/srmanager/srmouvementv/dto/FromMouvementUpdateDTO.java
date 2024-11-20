package ma.srmanager.srmouvementv.dto;

import lombok.Data;
import ma.srmanager.srmouvementv.entities.Fournisseur;
import ma.srmanager.srmouvementv.models.Marche;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class FromMouvementUpdateDTO {
    private Long vehiculeRouteId;
    private Marche marche;
    private Fournisseur fournisseur;
    private String bl;
    private Double blMontant;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateBl;



}
