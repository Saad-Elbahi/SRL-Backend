package org.example.enginservice.dto;

import lombok.Data;

@Data
public class EnginGpsDTO {
    private Long enginId;
    private String name;
    private String device;
    private String model;
    private Double coutH;
    private Long chauffeurId;
}
