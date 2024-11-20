package ma.srmanager.sraffaires.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBlocRequestDTO {
    private Long id;
    private Long numero;
    private Long marcheId;
    private String username;
    private String name;
    private Long nodeBordereauId;
    private Long nodeParentId;
    private Long numeroParent;
    private double tauxTVA;

    private boolean withFondation;


    private boolean withSousSol;
    private int nbrEtageSousSol;

    private boolean withSousSolDoubleHauteur;
    private int nbrEtageSousSolDoubleHauteur;


    private boolean withRdc;


    private boolean withRdcDoubleHauteur;


    private boolean withMezzanine;


    private boolean withEtages;
    private int nbrEtageElevation;


    private boolean withEtagesDoubleHauteur;
    private int nbrEtageElevationDoubleHauteur;


    private String token;

}
