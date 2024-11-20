package ma.srmanager.sraffaires.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.srmanager.coreapi.enums.marche.NodeBordereauLevel;
import ma.srmanager.coreapi.enums.marche.TypeProjet;
import ma.srmanager.sraffaires.entities.imputation.Marche;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeBordereauResponseDTO {

    private Long id;
    private Marche marche;
    private Long numeroParent;

    @Enumerated(EnumType.STRING)
    private NodeBordereauLevel nodeBordereauLevel;

    private Long numero;
    private String abrege;
    private String name;
    private String nameReduit;

    @Enumerated(EnumType.STRING)
    private TypeProjet typeProjet;

    private boolean inBordereau;

    private double MontantFraisMateriauxFournitures;
    private double MontantFraisMainOeuvre;
    private double MontantFraisFonctionnement;
    private double MontantFraisGeneraux;
    private double MontantMarge;

    private double montantMarcheHT;
    private double montantMarcheTVA;
    private double montantMarcheTTC;
    private String montantMarcheTTCString;

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

    private double montantRealisePrixStInvalideHT;
    private double montantRealisePrixStInvalideTVA;
    private double montantRealisePrixStInvalideTTC;



    private boolean checkedAllArticle;


}
