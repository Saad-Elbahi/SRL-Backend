package ma.srmanager.srnotification.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.srmanager.coreapi.enums.marche.ProjetStatus;
import ma.srmanager.coreapi.enums.marche.TypeProjet;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marche {
    private Long id;
    private String numero;
    private Long annee;
    private Long numOrdre;
    private String code;
    private String intitule;

    private String objet;
    private String bureauEtudeName;
    private String maitreOuvrageName;

    private TypeProjet typeProjet;

    private String adresseChantier;

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




}
