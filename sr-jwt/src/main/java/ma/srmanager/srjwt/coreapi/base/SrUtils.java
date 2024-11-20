package ma.srmanager.srjwt.coreapi.base;


import java.time.format.DateTimeFormatter;

public class SrUtils {
    public static final String FONDATION_SEMELLE = "Semelle";
    public static final String FONDATION_VOILLE_LONGRINE_INF = "Voile+Longrine inf";
    public static final String FONDATION_LONGRINE_SUP = "Longrine sup";
    public static final String FONDATION_REMBLAI = "Remblai";
    public static final String FONDATION_DALLAGE = "Dallage";

    public static final String BETON_POTEAUX = "Poteaux";
    public static final String BETON_VOILES = "Voiles";
    public static final String BETON_COFFRAGE_DALLE = "Coffrage dalle";
    public static final String BETON_FERRAILLAGE_DALLE = "Ferraillage dalle";
    public static final String BETON_COULAGE_DALLE = "Coullage dalle";

    public static final String ELEVATION_MACONNERIE = "Maçonnerie";
    public static final String ELEVATION_ENDUIT = "Enduit";


    public static final double QUOTA_SEMELLE = 30;
    public static final double QUOTA_VOILLE_LONGRINE_INF = 25;
    public static final double QUOTA_LONGRINE_SUP = 15;
    public static final double QUOTA_REMBLAI = 15;
    public static final double QUOTA_DALLAGE = 15;

    //=================== Avec Ferraillage =====================
    //    Poteaux	            15%     5,25%         5,75%
    //    Voiles	            15%     5,25%         5,75%
    //    Coffrage dalle	    25%     8,75%         9,58%
    //    Ferraillage dalle	    25%     8,75%         9,58%
    //    Coullage dalle	    20%     7%            7,67%
    //    tauxMaconnerie	            25%          28.33%
    //    tauxEnduit    	            30%          33.34%

    public static final double TAUX_BETON = 35;
    public static final double TAUX_BETON_FT = 38.33;

    public static final double TAUX_POTEAUX = 15;
    public static final double TAUX_VOILES = 15;
    public static final double TAUX_COFFRAGE_DALLE = 25;
    public static final double TAUX_FERRAILLAGE_DALLE = 25;
    public static final double TAUX_COULAGE_DALLE = 20;

    public static final double QUOTA_POTEAUX = 5.25;
    public static final double QUOTA_VOILES = 5.25;
    public static final double QUOTA_COFFRAGE_DALLE = 8.75;
    public static final double QUOTA_FERRAILLAGE_DALLE = 8.75;
    public static final double QUOTA_COULAGE_DALLE = 7;

    public static final double QUOTA_POTEAUX_FT = 5.75;
    public static final double QUOTA_VOILES_FT = 5.75;
    public static final double QUOTA_COFFRAGE_DALLE_FT = 9.58;
    public static final double QUOTA_FERRAILLAGE_DALLE_FT = 9.58;
    public static final double QUOTA_COULAGE_DALLE_FT = 7.67;

    public static final double TAUX_MACONNERIE = 25;
    public static final double TAUX_MACONNERIE_FT = 28.33;

    public static final double TAUX_ENDUIT = 30;
    public static final double TAUX_ENDUIT_FT = 33.34;

    //FIN DES TRAVAUX :FT

    // SANS FERRAILLAGE
    //=================== Sans Ferraillage =====================
    //    Poteaux	            20%     7%             7,67%
    //    Voiles	            20%     7%             7,67%
    //    Coffrage dalle	    35%     12,25%        13,42%
    //    Ferraillage dalle	    0%      0%                0%
    //    Coullage dalle	    25%     8,75%          9,59%
    //    tauxMaconnerie	            25%           28.33%
    //    tauxEnduit    	            30%           33.34%


    public static final  String gpsApiKey = "88918E46B26489F0ECDC7966541FE2A9";
    public static final  String gpsBaseUrl = "https://rouandigps.com/api/api.php";
    // private static Logger logger = Logger.getLogger(VehiculeGpsLocationServiceImpl.class.getName());
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    //Dev

    public static final String pointageBioTimeHost = "http://192.168.1.251:8010/";
    public static final String affaireHost = "http://localhost:6101";
    public static final String paramsHost = "http://localhost:6102";
    public static final String soustraitanceHost = "http://localhost:6103";
    public static final String caisseHost = "http://localhost:6104";
    public static final String rhHost = "http://localhost:6105";
    public static final String notifHost = "http://localhost:6106";
    public static final String storageHost = "http://localhost:6107";
    public static final String authHost = "http://localhost:6108";
    public static final String pointageHost = "http://localhost:6109";
    public static final String mailHost = "http://localhost:6110";
    public static final String FOLDER_PATH = System.getProperty("user.home") + "/Apps/sr-manager/RESSOURCES/data/upload";
    public static final String BACKUP_PATH = System.getProperty("user.home") + "/Apps/sr-manager/RESSOURCES/data/backup";
    public static final String sqlPath = "/Applications/XAMPP/xamppfiles/bin/mysqldump";
    public static final  String fileUploadDir = System.getProperty("user.home") + "/Apps/sr-manager/RESSOURCES/data/assets/images/";


    //Prod

//    public static final String pointageBioTimeHost = "http://102.50.254.72:8010";
//    public static final String affaireHost = "http://node116069-sr-affaires.jcloud-ver-jpe.ik-server.com:6101";
//    public static final String paramsHost = "http://node96154-sr-params.jcloud-ver-jpe.ik-server.com:6102";
//    public static final String soustraitanceHost = "http://node118212-env-sr-str.jcloud-ver-jpe.ik-server.com:6103";
//    public static final String caisseHost = "http://node108715-sr-reg-caisse.jcloud-ver-jpe.ik-server.com:6104";
//    public static final String rhHost = "http://node125049-sr-rh.jcloud-ver-jpe.ik-server.com:6105";
//    public static final String notifHost = "http://sr-notify.jcloud-ver-jpe.ik-server.com:6106";
//    public static final String storageHost = "http://node107681-sr-storage.jcloud-ver-jpe.ik-server.com:6107";
//    public static final String authHost = "http://node108757-sr-jwt.jcloud-ver-jpe.ik-server.com:6108";
//    public static final String mailHost = "http://node140637-sr-mail.jcloud-ver-jpe.ik-server.com:6110";
//    public static final String FOLDER_PATH = "/data/";
//    public static final String BACKUP_PATH = "/data/backup";
//    public static final String sqlPath = "/usr/bin/mysqldump";
//    public static final  String fileUploadDir = "/data/assets/images/";


    //Prod


    public static final String BUDGET_DIRECTION_EXPLOITATION = "BUDGET_DIRECTION_EXPLOITATION";
    public static final String BUDGET_DIRECTION_GENERALE = "BUDGET_DIRECTION_GENERALE";
    public static final String BUDGET_DIRECTION_TECHNIQUE = "BUDGET_DIRECTION_TECHNIQUE";
    public static final String BUDGET_LOGISTIQUE = "BUDGET_LOGISTIQUE";
    public static final String BUDGET_SERVICE_JURIDIQUE = "BUDGET_SERVICE_JURIDIQUE";
    public static final String BUDGET_DONS = "BUDGET_DONS";
    public static final String BUDGET_DROIT_ENREGISTREMENT_ET_TIMBRE = "BUDGET_DROIT_ENREGISTREMENT_ET_TIMBRE";
    public static final String BUDGET_FRAIS_DE_CHANTIER = "BUDGET_FRAIS_DE_CHANTIER";
    public static final String BUDGET_FRAIS_DEPLACEMENT = "BUDGET_FRAIS_DEPLACEMENT";
    public static final String BUDGET_FRAIS_WAFACASH = "BUDGET_FRAIS_WAFACASH";
    public static final String BUDGET_MENAGE_ET_CUISINE = "BUDGET_MENAGE_ET_CUISINE";
    public static final String BUDGET_SALAIRE = "BUDGET_SALAIRE";
    public static final String BUDGET_STR = "BUDGET_STR";
    public static final String BUDGET_SERVICE_IT = "BUDGET_SERVICE_IT";

    public static final String RETOUR_CAISSE_IT = "RETOUR_CAISSE_IT";
    public static final String RETOUR_CAISSE_DT = "RETOUR_CAISSE_IT";
    public static final String RETOUR_CAISSE_DG = "RETOUR_CAISSE_IT";
    public static final String RETOUR_CAISSE_EXPLOITATION = "RETOUR_CAISSE_IT";
    public static final String RETOUR_CAISSE_CHANTIER = "RETOUR_CAISSE_IT";
    public static final String RETOUR_FRAIS_LOGISTIQUE = "RETOUR_CAISSE_IT";
    public static final String RETOUR_FRAIS_JURIDIQUE = "RETOUR_CAISSE_IT";
    public static final String RETOUR_FRAIS_DEP = "RETOUR_CAISSE_IT";
    public static final String RETOUR_FRAIS_ENR = "RETOUR_CAISSE_IT";

    // BU
    public static final String BU_SOCIETE_ROUANDI = "SR";
    public static final String BU_RALU = "RALU";
    public static final String BU_RWOOD = "RWOOD";
    public static final String BU_RMETAL = "RMETAL";
    public static final String BU_RFLOW = "RFLOW";

    public static final String LIGNE_CAISSE_DEP = "LIGNE-CAISSE-DEP";
    public static final String LIGNE_CAISSE_DONS = "LIGNE-CAISSE-DONS";
    public static final String LIGNE_CAISSE_ENR = "LIGNE-CAISSE-ENR";
    public static final String LIGNE_CAISSE_JURIDIQUE = "LIGNE-CAISSE-JURIDIQUE";
    public static final String LIGNE_CAISSE_LOGISTIQUE = "LIGNE-CAISSE-LOGISTIQUE";
    public static final String LIGNE_CAISSE_MC = "LIGNE-CAISSE-MC";
    public static final String LIGNE_CAISSE_PRIME = "LIGNE-CAISSE-PRIME";
    public static final String LIGNE_CAISSE_SALAIRE = "LIGNE-CAISSE-SALAIRE";
    public static final String LIGNE_CAISSE_STC = "LIGNE-CAISSE-STC";
    public static final String LIGNE_CAISSE_STR = "LIGNE-CAISSE-STR";
    public static final String LIGNE_CAISSE_TIERS = "LIGNE-CAISSE-TIERS";
    public static final String LIGNE_CAISSE_CENTRALE = "LIGNE-CAISSE-CENTRALE";
    public static final String LIGNE_CAISSE_CHANTIER = "LIGNE-CAISSE-CHANTIER";
    public static final String LIGNE_CAISSE_DG = "LIGNE-CAISSE-DG";
    public static final String LIGNE_CAISSE_EXPLOITATION = "LIGNE-CAISSE-EXPLOITATION";
    public static final String LIGNE_CAISSE_IT = "LIGNE-CAISSE-IT";
    public static final String LIGNE_CAISSE_DT = "LIGNE-CAISSE-DT";

    public static final String LIGNE_FOND_CAISSE_ADMIN = "LIGNE-FOND-CAISSE-ADMIN";
    public static final String LIGNE_FOND_CAISSE_CHANTIER = "LIGNE-FOND-CAISSE-CHANTIER";
    public static final String LIGNE_FOND_CAISSE_EXPLOITATION = "LIGNE-FOND-CAISSE-EXPLOITATION";
    public static final String LIGNE_FOND_CAISSE_DG = "LIGNE-FOND-CAISSE-DG";
    public static final String LIGNE_FOND_CAISSE_TECHNIQUE = "LIGNE-FOND-CAISSE-TECHNIQUE";
    public static final String LIGNE_FOND_CAISSE_IT = "LIGNE-FOND-CAISSE-IT";
    public static final String LIGNE_FOND_CAISSE_CENTRALE = "LIGNE-FOND-CAISSE-CENTRALE";


}
