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
