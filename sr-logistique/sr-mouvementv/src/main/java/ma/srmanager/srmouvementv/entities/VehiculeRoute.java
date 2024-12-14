package ma.srmanager.srmouvementv.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehiculeRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Date")
    private LocalDate date;

    private double routeLength;


    @OneToMany(mappedBy = "vehiculeRoute")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<FromMouvement> fromMouvements = new ArrayList<>();

    @Column(name = "cost_per_trip")
    private double costPerTrip; // Coût / voyage

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id", nullable = false)
    private VehiculeGpsLocation vehiculeGpsLocation;

    @OneToMany(mappedBy = "vehiculeRoute")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TripImputation> imputations = new ArrayList<>();

    private double fillingPercentage;
    private double fillingCost;
    private Boolean fromStatus;
    private Boolean imputationStatus;

    @PostLoad
    public void postLoadCalculations() {
        calculateCostPerTrip();
        calculateFillingCost();
    }

    public void calculateCostPerTrip() {
        if (this.vehiculeGpsLocation != null && this.vehiculeGpsLocation.getCostPerKm() != null) {
            this.costPerTrip = this.vehiculeGpsLocation.getCostPerKm() * this.routeLength;
        } else {
            // Handle the case where vehiculeGpsLocation or costPerKm is null
            // You might set a default value or log a warning
            this.costPerTrip = 0.0; // Default value or handle as needed
        }
    }

    public void calculateFillingCost() {
        if (costPerTrip > 0 && fillingPercentage >= 0) {
            fillingCost = costPerTrip * (fillingPercentage / 100);
        } else {
            fillingCost = 0.0;
        }
    }

    public void addFromMouvement(FromMouvement fromMouvement) {
        fromMouvements.add(fromMouvement);
        fromMouvement.setVehiculeRoute(this);
    }

    public void removeFromMouvement(FromMouvement fromMouvement) {
        fromMouvements.remove(fromMouvement);
        fromMouvement.setVehiculeRoute(null);
    }
}
