package ma.srmanager.srmouvementv.model;

import javax.persistence.*;
import lombok.*;

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
    @Column(nullable = true)
    private Long fournisseurid;

    private String fournisseurName;

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
