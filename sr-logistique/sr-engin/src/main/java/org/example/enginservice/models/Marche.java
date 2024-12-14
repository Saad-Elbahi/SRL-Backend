package org.example.enginservice.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Marche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @Lob
    private String intitule;
    private String typeProjet;
    private String villeintitule;
    private String chefZoneFullName;
    private String chefZoneUsername;
}
