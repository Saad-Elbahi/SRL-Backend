package org.example.enginservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class EnginGpsLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String imei;

    private String name;

    private String device;

    private String model;

    private Double coutH;
    @ManyToOne
    private Chauffeur chauffeur;

    private String plateNumber;

    @OneToMany(mappedBy = "enginGpsLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EnginRoute> enginRoutes;

}