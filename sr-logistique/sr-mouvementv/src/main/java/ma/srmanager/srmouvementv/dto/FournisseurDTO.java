package ma.srmanager.srmouvementv.dto;

import lombok.Data;

@Data
public class FournisseurDTO {
    private Long id;
    private String abbreviationFournisseur;
    private String intituleFournisseur;
    private String adresseFournisseur;
    private String ice;
    private String email;
    private String telephone;
    private String contact;
}
