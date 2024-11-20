package ma.srmanager.srmouvementv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehiculeGpsLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    //@NotNull
    @JsonProperty("group_name")
    private String groupName;

    //@NotNull
    private String imei;

    private String name;
    private String device;
    private String model;

    @JsonProperty("plate_number")
    private String plateNumber;
    @Column(name = "cost_per_km")
    private Double costPerKm; // Coût/km

    @ManyToOne
    @JoinColumn(name = "chauffeur_id")
    private Chauffeur chauffeur;

    @OneToMany(mappedBy = "vehiculeGpsLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<VehiculeRoute> vehiculeRoutes;

    @OneToMany(mappedBy = "vehiculeGpsLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<FromMouvement> fromMouvements;
}
