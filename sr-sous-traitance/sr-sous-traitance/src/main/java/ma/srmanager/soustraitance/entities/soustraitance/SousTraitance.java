package ma.srmanager.soustraitance.entities.soustraitance;

import lombok.*;
import ma.srmanager.coreapi.enums.soustraitance.BorderColor;
import ma.srmanager.coreapi.enums.soustraitance.SousTraitanceStatus;
import ma.srmanager.coreapi.enums.soustraitance.SuiviAttachementStatus;
import ma.srmanager.coreapi.enums.soustraitance.TimeLineCreateStLevel;
import ma.srmanager.coreapi.enums.subcontractor.SoldeStatus;
import ma.srmanager.soustraitance.entities.subcontractor.SubContractor;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SousTraitance {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numero;

    private Long marcheId;
    private String marcheCode;

    @Column(name = "marcheObjet",length=1000)
    private String marcheObjet;

    private int jour;
    private int mois;
    private int trimestre;
    private int semestre;
    private int annee;

    private LocalDate dateDebutConsultation;
    private LocalDate dateFinConsultation;
    private LocalDate datePv;

    private LocalDate dateOrdreService;
    private LocalDate dateMiseAjour;

    private LocalDate dateFinPrevue;
    private LocalDate dateFinReelle;

    private String natureTravaux;
    private String description;
    private String objet;

    private double dureeEnJourPrevue;
    private double dureeEnJourReelle;

    private double dureeEnMoisPrevue;
    private double dureeEnMoisReelle;

    private String dureeEnJourPrevueString;
    private String dureeEnJourReelleString;

    private String dureeEnMoisPrevueString;
    private String dureeEnMoisReelleString;

    @Enumerated(EnumType.STRING)
    private SousTraitanceStatus sousTraitanceStatus;

    @Enumerated(EnumType.STRING)
    private SuiviAttachementStatus suiviAttachementStatus;

    @Enumerated(EnumType.STRING)
    private SoldeStatus soldeStatus;

    private double tauxRealisation;
    private double tauxRealisationIdeal;

    private double tauxJournalierIdeal;
    private double tauxMensuelIdeal;
    private Long dureeTempTravaux;
    private double irji; //Indice de Realisation Journaliere Ideal


    @ManyToOne
    private SubContractor subContractor;

    private Long numContrat;

    private boolean contractExported;

    private double tauxTVA;
    private double tauxRetenueGarentie;

    private double montantPenaliteParJour;
    private String montantPenaliteParJourString;

    private int nombreJourRetard;
    private double montantPenaliteRetard;

    private double montantMarcheHT;
    private double montantMarcheTVA;
    private double montantMarcheTTC;
    private String montantMarcheTTCString;

    private double montantSousTraitanceHT;
    private double montantSousTraitanceTVA;
    private double montantSousTraitanceTTC;
    private String montantSousTraitanceTTCString;
    //private String montantSousTraitanceHTString;

    private double montantRealisePrixMarcheHT;
    private double montantRealisePrixMarcheTVA;
    private double montantRealisePrixMarcheTTC;

    private double montantRealisePrixStHT;
    private double montantRealisePrixStTVA;
    private double montantRealisePrixStTTC;

    private double lastMountRealizedTTC;

    private double montantGarantieHT;
    private double montantGarantieTVA;
    private double montantGarantieTTC;

    private double deduction;

    private double montantApayerHT;
    private double montantApayerTVA;
    private double montantApayerTTC;
    private String montantApayerTTCString;

    private double montantPayerHT;
    private double montantPayerTVA;
    private double montantPayerTTC;
    private String montantPayerTTCString;



    private double montantAvanceTotal;
    private double montantAvanceEspece;
    private double montantAvanceVirement;
    private double montantAvanceCheque;
    private double montantAvanceEffet;

    private double montantHistoTotal;
    private double montantHistoEspece;
    private double montantHistoVirement;
    private double montantHistoCheque;
    private double montantHistoEffet;

    private double totalEngagement;
    private double montantEngagementEspece;
    private double montantEngagementVirement;
    private double montantEngagementCheque;
    private double montantEngagementEffet;

    private double totalPayer;
    private double montantPayerEspece;
    private double montantPayerVirement;
    private double montantPayerCheque;
    private double montantPayerEffet;


    private double montantRealisePrixStInvalideHT;
    private double montantRealisePrixStInvalideTVA;
    private double montantRealisePrixStInvalideTTC;

    private double invoicedAmount;
    private double uninvoicedAmount;
    
    private double soldeStc;
    private double soldeStcEnCours;

    private double solde;
    private double soldeEnCours;

    private double montantProvision;

    @Enumerated(EnumType.STRING)
    private TimeLineCreateStLevel timeLineCreateStLevel;

    private int stepNumber;

    private boolean referencesValidated;
    private boolean articlesValidated;
    private boolean subContractorsValidated;
    private boolean firstDevisReceved;
    private boolean priceValidated;
    private boolean mieuxDisantValidated;
    private boolean contratValidated;

    @Enumerated(EnumType.STRING)
    private BorderColor borderColor;

    private boolean attachementGo;

    private boolean stc;
    //private boolean releaseWarranty;

    //
    private boolean locked;

    private boolean inputLocked;
    private boolean validationCzLocked;
    private boolean validationRespStrLocked;
    private boolean validationDgLocked;

    private boolean canEditSubContractor;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SousTraitance that = (SousTraitance) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
