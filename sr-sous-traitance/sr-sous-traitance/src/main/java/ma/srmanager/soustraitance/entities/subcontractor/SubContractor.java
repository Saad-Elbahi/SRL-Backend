package ma.srmanager.soustraitance.entities.subcontractor;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.enums.contact.Civilite;
import ma.srmanager.coreapi.enums.subcontractor.AccountMovementType;
import ma.srmanager.coreapi.enums.subcontractor.ModeTravail;
import ma.srmanager.coreapi.enums.subcontractor.SubContractorCategorie;
import ma.srmanager.coreapi.enums.subcontractor.SubContractorStatus;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class SubContractor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numero;

    //Gerant
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
    private String prenom;
    private String nom;
    private String cin;
    private String gsm;
    private String avatar;
    private String email;

    //Entreprise
    private String fullName;
    private String adresse;

    @Transient
    private Param ville;
    private Long villeId;
    private String villeIntitule;

    @Enumerated(EnumType.STRING)
    private SubContractorStatus subContractorStatus;
    @Enumerated(EnumType.STRING)
    private SubContractorCategorie subContractorCategorie; // Entreprise-PP
    @Enumerated(EnumType.STRING)
    private ModeTravail modeTravail;//Avec Contrat- sans Contrat

    private String ice;
    private String indiceFiscal;
    private String patente;
    private String rc;
    private String cnss;
    private String rib;

    private LocalDate dateCreation;

    private double initialBalance;
    @Enumerated(EnumType.STRING)
    private AccountMovementType initialBalanceType;

    private double credit;
    private double debit;
    private double solde;
    private double soldeEnCours;

    private String cinImage;
    private String statutImage;
    private String rcImage;
    private String patenteImage;
    private String cnssImage;
    private String atImage;
    private String ribImage;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SubContractor that = (SubContractor) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
