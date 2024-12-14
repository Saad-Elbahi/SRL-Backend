package org.example.enginservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnginImputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long affaireId;
    private String affaireCode;

    private Long subContractorId;
    private String subContractorFullName;

    private Double nbrHImputation;

    private String observation;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Lot lot;


    private Double costImputation;

    @ManyToOne
    private EnginRoute enginRoute;

    @PostPersist
    @PostUpdate
    public void calculateCostImputation() {
        if (this.enginRoute.getEnginGpsLocation().getCoutH() != null && this.nbrHImputation != null) {
            this.costImputation = this.enginRoute.getEnginGpsLocation().getCoutH() * this.nbrHImputation ;
        } else {
            this.costImputation = 0.0;
        }
    }
}
