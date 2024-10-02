package ma.srmanager.srmouvementv.model;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class FromMouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    private Affaire affaire;

    @ManyToOne(fetch = FetchType.EAGER)
    private Fournisseur fournisseur;

    private String bl;
    private Double blMontant;
    private Date dateBl;
    private String departement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_affaire_id")
    private Affaire toAffaire;  //TO

    @ManyToOne
    @JoinColumn(name = "vehicule_route_id")
    private VehiculeRoute vehiculeRoute;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private VehiculeGpsLocation vehiculeGpsLocation;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        FromMouvement that = (FromMouvement) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
