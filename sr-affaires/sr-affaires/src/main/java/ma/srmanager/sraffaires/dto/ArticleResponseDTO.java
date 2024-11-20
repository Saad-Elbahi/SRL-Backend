package ma.srmanager.sraffaires.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.srmanager.coreapi.enums.marche.ArticleFollowedBy;
import ma.srmanager.coreapi.enums.marche.ArticleRealisationStatus;
import ma.srmanager.coreapi.enums.marche.TypeProjet;
import ma.srmanager.coreapi.enums.soustraitance.ArticleSousTraitanceStatus;
import ma.srmanager.coreapi.enums.soustraitance.RubriqueSousTraitance;

import ma.srmanager.sraffaires.entities.imputation.Marche;
import org.springframework.data.repository.query.Param;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDTO {

    private Long id;
    private Marche marche;

    private Param unite;

    private boolean inBordereau;

    private Long numero;
    private String code;
    private String name;
    private String nameReduit;

    @Enumerated(EnumType.STRING)
    private TypeProjet typeProjet;

    private double prixUnitaireHT;
    private double tauxTVA;

    private double quantiteMarche;
    private double quantiteAvantMetre;
    private double quantiteMetre;

    private double tauxFraisMateriauxFournitures;
    private double tauxFraisMainOeuvre;
    private double tauxFraisFonctionnement;
    private double tauxFraisGeneraux;
    private double tauxMarge;

    private double montantFraisMateriauxFournitures;
    private double montantFraisMainOeuvre;
    private double montantFraisFonctionnement;
    private double montantFraisGeneraux;
    private double montantMarge;


    private double tauxSt;
    private double quantiteSt;
    private double quantiteNst;

    private double tauxRealise;
    private double quantiteRealisee;
    private double quantiteNonRealisee;

    private double montantMarcheHT;
    private double montantMarcheTVA;
    private double montantMarcheTTC;
    private String montantMarcheTTCString;

    //private double montantSousTraitanceHT;
    //private double montantSousTraitanceTVA;
    private double montantSousTraitanceTTC;
    private String montantSousTraitanceTTCString;

    private double montantRealisePrixMarcheHT;
    private double montantRealisePrixMarcheTVA;
    private double montantRealisePrixMarcheTTC;

    //private double montantRealisePrixStHT;
   // private double montantRealisePrixStTVA;
    private double montantRealisePrixStTTC;

    //private double montantGarantieHT;
    //private double montantGarantieTVA;
    private double montantGarantieTTC;

    //private double montantApayerHT;
    //private double montantApayerTVA;
    private double montantApayerTTC;
    private String montantApayerTTCString;

    //private double montantDecompteHT;
    //private double montantDecompteTVA;
    private double montantDecompteTTC;
    private String montantDecompteTTCString;

    //private double montantRealisePrixStInvalideHT;
    //private double montantRealisePrixStInvalideTVA;
    private double montantRealisePrixStInvalideTTC;

    @Enumerated(EnumType.STRING)
    private ArticleRealisationStatus articleRealisationStatus;

    @Enumerated(EnumType.STRING)
    private ArticleSousTraitanceStatus articleSousTraitanceStatus;

    @Enumerated(EnumType.STRING)
    private RubriqueSousTraitance rubriqueSousTraitance;

    @Enumerated(EnumType.STRING)
    private ArticleFollowedBy articleFollowedBy;

    private double quantiteAst;
    private boolean stTotale;
    private boolean ischecked;





}
