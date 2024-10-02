package ma.srmanager.srmouvementv.dto;

import lombok.Data;
import ma.srmanager.srmouvementv.model.Affaire;
import ma.srmanager.srmouvementv.model.Fournisseur;

import java.util.Date;
@Data
public class FromMouvementUpdateDTO {
    private Long vehiculeRouteId;
    private Affaire affaire;
    private Fournisseur fournisseur;
    private String bl;
    private Double blMontant;
    private Date dateBl;
    private String departement;
    private Affaire toAffaire;
}
