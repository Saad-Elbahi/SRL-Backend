package ma.srmanager.srmouvementv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Affaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @Lob
    private String intitule;
    private String typeProjet;
    private String villeintitule;
    private String chefZoneFullName;
    private String chefZoneUsername;
}
