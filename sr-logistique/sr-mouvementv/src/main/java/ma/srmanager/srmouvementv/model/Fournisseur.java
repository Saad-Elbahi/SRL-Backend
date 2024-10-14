package ma.srmanager.srmouvementv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Fournisseur  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String abbreviationFournisseur;
    private String intituleFournisseur;
    private String adresseFournisseur;
    private String ice;
    private String email;
    private String telephone;
    private String contact;
}
