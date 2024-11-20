package ma.srmanager.coreapi.enums.global;

import lombok.Getter;

public enum SourceDataImport {
    BOOLEAN("boolean", "boolean"),
    INT("int", "int"),
    INTEGER("Integer", "java.lang.Integer"),
    LONG("Long", "java.lang.Long"),
    LONG_KOTLIN("long", "long"),
    STRING("String", "java.lang.String"),
    FLOAT("Double", "java.lang.Float"),
    FLOAT_PRIM("Double", "float"),
    DOUBLE("Double", "java.lang.Double"),
    DOUBLE_PRIM("double", "double"),
    DATE("Date", "java.util.Date"),
    LOCALDATE("LocalDate", "java.time.LocalDate"),
    CALENDAR("Calendar", "java.util.Calendar"),
    CreateParamRequestDTO("CreateParamRequestDTO", "ma.srmanager.coreapi.params.dtos.CreateParamRequestDTO"),
    CreateMarcheRequestDTO("CreateMarcheRequestDTO", "ma.srmanager.coreapi.affaire.dtos.marche.CreateMarcheRequestDTO"),
    CreateNodeRequestDTO("CreateNodeRequestDTO", "ma.srmanager.coreapi.affaire.dtos.nodebordereau.CreateNodeRequestDTO"),
    CreateArticleRequestDTO("CreateArticleRequestDTO", "ma.srmanager.coreapi.affaire.dtos.article.CreateArticleRequestDTO"),
    ImportArticleRequestDTO("ImportArticleRequestDTO", "ma.srmanager.coreapi.affaire.dtos.article.ImportArticleRequestDTO"),
    CreateContactRequestDTO("CreateContactRequestDTO", "ma.srmanager.coreapi.contacts.contacts.dtos.CreateContactRequestDTO"),
    CreateLigneCaisseSalaireDTO("CreateLigneCaisseSalaireDTO", "ma.srmanager.coreapi.caisse.CreateLigneCaisseSalaireDTO"),
    CreateRequestDTO("CreateRequestDTO", "ma.srmanager.coreapi.soustraitance.subcontractor.dtos.CreateRequestDTO"),
    ParamType("ParamType", "ma.srmanager.coreapi.enums.global.ParamType"),
    ProjetStatus("ProjetStatus", "ma.srmanager.coreapi.enums.marche.ProjetStatus"),
    MoyenPaiement("MoyenPaiement", "ma.srmanager.coreapi.enums.global.MoyenPaiement"),
    ArticleFollowedBy("ArticleFollowedBy", "ma.srmanager.coreapi.enums.marche.ArticleFollowedBy"),
    ArticleSousTraitanceStatus("ArticleSousTraitanceStatus", "ma.srmanager.coreapi.enums.soustraitance.ArticleSousTraitanceStatus"),
    ArticleRealisationStatus("ArticleRealisationStatus", "ma.srmanager.coreapi.enums.marche.ArticleRealisationStatus"),
    Civilite("Civilite", "ma.srmanager.coreapi.enums.contact.Civilite"),
    RubriqueSousTraitance("RubriqueSousTraitance", "ma.srmanager.coreapi.enums.soustraitance.RubriqueSousTraitance"),
    SituationFamiliale("SituationFamiliale", "ma.srmanager.coreapi.enums.contact.SituationFamiliale"),
    NodeBordereauLevel("NodeBordereauLevel", "ma.srmanager.coreapi.enums.marche.NodeBordereauLevel"),
    TypeProjet("ParamType", "ma.srmanager.coreapi.enums.marche.TypeProjet"),

    LigneRegistreCaisseDeplacement("LigneRegistreCaisseDeplacement", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseDeplacement"),
    LigneRegistreCaisseDons("LigneRegistreCaisseDons", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseDons"),
    LigneRegistreCaisseEnrTimbre("LigneRegistreCaisseEnrTimbre", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseEnrTimbre"),
    LigneRegistreCaisseJuridique("LigneRegistreCaisseJuridique", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseJuridique"),
    LigneRegistreCaisseLogistique("LigneRegistreCaisseLogistique", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseLogistique"),
    LigneRegistreCaisseMenageCuisine("LigneRegistreCaisseMenageCuisine", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseMenageCuisine"),
    LigneRegistreCaissePrime("LigneRegistreCaissePrime", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaissePrime"),
    LigneRegistreCaisseSalaire("LigneRegistreCaisseSalaire", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseSalaire"),
    LigneRegistreCaisseStc("LigneRegistreCaisseStc", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseStc"),
    LigneRegistreCaisseStr("LigneRegistreCaisseStr", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseStr"),
    LigneRegistreCaisseTiers("LigneRegistreCaisseTiers", "ma.srmanager.srcaisse.entites.lines.admin.LigneRegistreCaisseTiers"),

    LigneRegistreCaisseCentrale("LigneRegistreCaisseCentrale", "ma.srmanager.srcaisse.entites.lines.centrale.LigneRegistreCaisseCentrale"),
    LigneRegistreCaisseChantier("LigneRegistreCaisseChantier", "ma.srmanager.srcaisse.entites.lines.chantier.LigneRegistreCaisseChantier"),
    LigneRegistreCaisseDirGenerale("LigneRegistreCaisseDirGenerale", "ma.srmanager.srcaisse.entites.lines.dg.LigneRegistreCaisseDirGenerale"),
    LigneRegistreCaisseDirExploitation("LigneRegistreCaisseDirExploitation", "ma.srmanager.srcaisse.entites.lines.exploitation.LigneRegistreCaisseDirExploitation"),
    LigneRegistreCaisseServiceInfo("LigneRegistreCaisseServiceInfo", "ma.srmanager.srcaisse.entites.lines.it.LigneRegistreCaisseServiceInfo"),
    LigneRegistreCaisseDirTechnique("LigneRegistreCaisseDirTechnique", "ma.srmanager.srcaisse.entites.lines.technique.LigneRegistreCaisseDirTechnique"),
    LigneFondCaisseAdmin("LigneFondCaisseAdmin", "ma.srmanager.srcaisse.entites.lines.admin.fonds.LigneFondCaisseAdmin"),
    LigneFondCaisseChantier("LigneFondCaisseChantier", "ma.srmanager.srcaisse.entites.lines.admin.fonds.LigneFondCaisseChantier"),
    LigneFondCaisseDirExploitation("LigneFondCaisseDirExploitation", "ma.srmanager.srcaisse.entites.lines.admin.fonds.LigneFondCaisseDirExploitation"),
    LigneFondCaisseDirGenerale("LigneFondCaisseDirGenerale", "ma.srmanager.srcaisse.entites.lines.admin.fonds.LigneFondCaisseDirGenerale"),
    LigneFondCaisseDirTechnique("LigneFondCaisseDirTechnique", "ma.srmanager.srcaisse.entites.lines.admin.fonds.LigneFondCaisseDirTechnique"),
    LigneFondCaisseServiceInfo("LigneFondCaisseServiceInfo", "ma.srmanager.srcaisse.entites.lines.admin.fonds.LigneFondCaisseServiceInfo"),
    LigneFondCaisseCentrale("LigneFondCaisseCentrale", "ma.srmanager.srcaisse.entites.lines.centrale.LigneFondCaisseCentrale");

    @Getter
    private final String shortClasseName;
    @Getter
    private final String fullClassName;

    SourceDataImport(String shortClasseName, String fullClassName) {
        this.shortClasseName = shortClasseName;
        this.fullClassName = fullClassName;
    }


    public static String fullClassNameOfByclasseName(final String shortClasseName) {

        if (shortClasseName == null || shortClasseName.isEmpty()) {
            throw new IllegalArgumentException("Le Nom de la classe pas etre vide.");
        }

        for (SourceDataImport sdi : values()) {
            if (sdi.shortClasseName.equalsIgnoreCase(shortClasseName)) {
                return sdi.fullClassName;
            }
        }

        throw new IllegalArgumentException("Le Nom de la classe demandée n'existe pas.");
    }

    @Override
    public String toString() {

        return this.fullClassName;
    }
}
