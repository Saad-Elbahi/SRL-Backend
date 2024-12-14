package org.example.enginservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class
EnginRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private double routeLength;

    private String drivesDuration;
    private String engineIdle;
    private Double nbrH;

    private String rendement;
    private Double montant;
    private Boolean imputationStatus;

    @OneToMany(mappedBy = "enginRoute")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<EnginImputation> imputations = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enginGpsLocation_id", nullable = false)
    private EnginGpsLocation enginGpsLocation;

}
