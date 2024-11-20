package ma.srmanager.sraffaires.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.srmanager.coreapi.enums.soustraitance.SuiviAttachementStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvancementResponseDTO {

    private Long id;
    private Long numero;

    private LocalDate dateSuivi;

    private int mois;
    private int trimestre;
    private int semestre;
    private int annee;



    private double quantiteRealisee;
    private double tauxRealise;

    //private double montantRealisePrixStHT;
    //private double montantRealisePrixStTVA;
    private double montantRealisePrixStTTC;

    //private double montantGarantieHT;
    //private double montantGarantieTVA;
    private double montantGarantieTTC;

    //private double montantApayerHT;
    //private double montantApayerTVA;
    private double montantApayerTTC;

    @Enumerated(EnumType.STRING)
    private SuiviAttachementStatus suiviAttachementStatus;

}
