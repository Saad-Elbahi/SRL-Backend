package ma.srmanager.srmouvementv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.srmanager.srmouvementv.models.Affaire;
import ma.srmanager.srmouvementv.models.SubContractor;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripImputation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @ManyToOne
    @JoinColumn(name = "affaire_id", nullable = false)
    private Affaire affaire;*/


    /*@ManyToOne
    private Soustraitant soustraitant;*/


    private Long affaireId;
    private String affaireCode;

    private Long subContractorId;
    private String subContractorFullName;

    @Column(name = "filling_percentage")
    private Double fillingPercentage; // % de remplissage

    @Column(name = "observation")
    private String observation;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Lot lot;


    @Column(name = "cost_imputation")
    private Double costImputation;

    @ManyToOne
    @JoinColumn(name = "vehicule_route_id")
    private VehiculeRoute vehiculeRoute;

    @PostPersist
    @PostUpdate
    public void calculateCostImputation() {
        if (this.vehiculeRoute != null && this.fillingPercentage != null) {
            this.costImputation = this.vehiculeRoute.getCostPerTrip() * (this.fillingPercentage / 100);
        } else {
            this.costImputation = 0.0;
        }
    }


}
