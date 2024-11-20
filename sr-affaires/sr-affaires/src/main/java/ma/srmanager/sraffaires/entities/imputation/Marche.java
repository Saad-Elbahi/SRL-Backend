package ma.srmanager.sraffaires.entities.imputation;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.srmanager.coreapi.enums.marche.ProjetStatus;
import ma.srmanager.coreapi.enums.marche.TypeProjet;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("MARCHE")
public class Marche extends ChampImputation {

    private String reference;
    private Long annee;
    private Long numOrdre;


    @Column(name = "objet",length=1000)
    private String objet;
    private String bureauEtudeName;
    private String maitreOuvrageName;

    @Enumerated(EnumType.STRING)
    private TypeProjet typeProjet;

    private String adresseChantier;

    @Enumerated(EnumType.STRING)
    private ProjetStatus projetStatus;


    private int dayOrdreService;
    private int monthOrdreService;
    private int yearOrdreService;

    private LocalDate dateOrdreService;
    private LocalDate dateFinPrevue;
    private LocalDate dateFinReelle;

    private Long dureeEnJourPrevue;
    private Long dureeEnJourReelle;
    private Long dureeEnMoisPrevue;
    private Long dureeEnMoisReelle;

    private Long delaisMois;
    private Long delaiAvenantMois;

    private Long villeId;

//    @ManyToOne
//    private Contact bureauEtude;
//    @ManyToOne
//    private Contact maitreOuvrage;

    // A supprimer
    private String villeintitule;
    private String maitreOuvrageIntitule;

    private Long directeurProjetId;
    private Long chefZoneId;
    private Long conducteurId;
    private Long technicienId;
    private Long comptableId;
    private Long pointeurId;
    private Long magazinierId;
    private Long chefChantierId;
    private Long gardienJourId;
    private Long gardienNuitId;

    private String directeurProjetFullName;
    private String chefZoneFullName;
    private String conducteurFullName;
    private String technicienFullName;
    private String comptableFullName;
    private String pointeurFullName;
    private String magazinierFullName;
    private String chefChantierFullName;
    private String gardienJourFullName;
    private String gardienNuitFullName;

    private String directeurProjetUsername;
    private String chefZoneUsername;
    private String ingenieurUsername;
    private String conducteurUsername;
    private String technicienUsername;
    private String comptableUsername;
    private String pointeurUsername;
    private String magazinierUsername;
    private String chefChantierUsername;
    private String gardienJourUsername;
    private String gardienNuitUsername;

    private String directeurProjetAvatar;
    private String chefZoneAvatar;
    private String ingenieurAvatar;
    private String conducteurAvatar;
    private String technicienAvatar;
    private String comptableAvatar;
    private String pointeurAvatar;
    private String magazinierAvatar;
    private String chefChantierAvatar;
    private String gardienJourAvatar;
    private String gardienNuitAvatar;
// fin a supp

   /* @OneToMany(mappedBy = "marche")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<NodeBordereau> nodes=new ArrayList<>();
*/
    private double montantMarcheHT;
    private double montantMarcheTVA;
    private double montantMarcheTTC;
    private String montantMarcheTTCString;

    private double montantMarcheHBHT;
    private double montantMarcheHBTVA;
    private double montantMarcheHBTTC;
    private String montantMarcheHBTTCString;

    private double montantSousTraitanceHT;
    private double montantSousTraitanceTVA;
    private double montantSousTraitanceTTC;
    private String montantSousTraitanceTTCString;

    private double montantRealisePrixMarcheHT;
    private double montantRealisePrixMarcheTVA;
    private double montantRealisePrixMarcheTTC;

    private double montantRealisePrixStHT;
    private double montantRealisePrixStTVA;
    private double montantRealisePrixStTTC;

    private double montantGarantieHT;
    private double montantGarantieTVA;
    private double montantGarantieTTC;

    private double montantApayerHT;
    private double montantApayerTVA;
    private double montantApayerTTC;
    private String montantApayerTTCString;

    private double montantDecompteHT;
    private double montantDecompteTVA;
    private double montantDecompteTTC;
    private String montantDecompteTTCString;

    private double montantFraisMateriauxFournitures;
    private double montantFraisMainOeuvre;
    private double montantFraisFonctionnement;
    private double montantFraisGeneraux;
    private double montantMarge;

    private double montantFraisMateriauxFournituresHB;
    private double montantFraisMainOeuvreHB;
    private double montantFraisFonctionnementHB;
    private double montantFraisGenerauxHB;
    private double montantMargeHB;

    private double montantRealisePrixStInvalideHT;
    private double montantRealisePrixStInvalideTVA;
    private double montantRealisePrixStInvalideTTC;

    private double tauxRealise;

//    private boolean allowStr;
//    private boolean allowTresorerie;
//    private boolean allowPointage;
//    private boolean allowDrive;

}
