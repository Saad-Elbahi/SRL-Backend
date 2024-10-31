package ma.srmanager.srmouvementv.model;

import javax.persistence.*;
import lombok.*;

import org.hibernate.proxy.HibernateProxy;

import java.util.Date;


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

    private Long affaireId;
    private String affaireCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Fournisseur fournisseur;

    private String bl;
    private Double blMontant;
    private Date dateBl;


    @ManyToOne
    @JoinColumn(name = "vehicule_route_id")
    private VehiculeRoute vehiculeRoute;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private VehiculeGpsLocation vehiculeGpsLocation;

}
