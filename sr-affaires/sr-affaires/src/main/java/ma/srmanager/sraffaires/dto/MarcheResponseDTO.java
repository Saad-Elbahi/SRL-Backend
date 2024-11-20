package ma.srmanager.sraffaires.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.srmanager.coreapi.enums.marche.ProjetStatus;
import ma.srmanager.coreapi.enums.marche.TypeProjet;
import org.springframework.data.repository.query.Param;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcheResponseDTO {
    private Long id;
    private String numero;
    private Long annee;
    private Long numOrdre;
    private String code;
    private String intitule;
    private String objet;
    private Param Ville;

    private String bureauEtudeName;
    private String maitreOuvrageName;

    @Enumerated(EnumType.STRING)
    private TypeProjet typeProjet;

    private String adresseChantier;

    @Enumerated(EnumType.STRING)
    private ProjetStatus projetStatus;


    private LocalDate dateOrdreService;
    private LocalDate dateFinPrevue;
    private LocalDate dateFinReelle;
    private Long dureeEnJourPrevue;
    private Long dureeEnJourReelle;
    private Long dureeEnMoisPrevue;
    private Long dureeEnMoisReelle;

    private Long delaisMois;
    private Long delaiAvenantMois;

//    @ManyToOne
//    private Contact bureauEtude;
//    @ManyToOne
//    private Contact maitreOuvrage;

    private Long directeurProjetId;
    private Long conducteurId;
    private Long comptableId;
    private Long chefChantierId;
    private Long technicienId;
    private Long pointeurId;
    private Long magazinierId;
    private Long gardienJourId;
    private Long gardienNuitId;


    // A supprimer
    private String villeintitule;
    private String directeurProjetNomPrenom;
    private String directeurProjetFullName;
    private String conducteurFullName;
    private String comptableFullName;
    private String chefChantierFullName;
    private String technicienFullName;
    private String pointeurFullName;
    private String magazinierFullName;
    private String gardienJourFullName;
    private String gardienNuitFullName;
    private String maitreOuvrageIntitule;



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

}
