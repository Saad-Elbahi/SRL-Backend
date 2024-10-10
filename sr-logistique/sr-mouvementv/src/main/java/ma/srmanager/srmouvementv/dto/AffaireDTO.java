package ma.srmanager.srmouvementv.dto;

import lombok.Data;

@Data
public class AffaireDTO {
    private Long id;
    private String code;
    private String typeProjet;
    private String villeintitule;
    private String chefZoneFullName;
    private String chefZoneUsername;
}
