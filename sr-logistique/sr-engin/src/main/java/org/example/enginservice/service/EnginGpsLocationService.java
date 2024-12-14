package org.example.enginservice.service;

import org.example.enginservice.dto.EnginGpsDTO;
import org.example.enginservice.entities.EnginGpsLocation;

import java.util.List;

public interface EnginGpsLocationService {
    void saveEnginGpsLocationFromApi() throws Exception ;
    EnginGpsLocation updateEnginGpsLocation(EnginGpsDTO dto);
    List<EnginGpsLocation> getAllVehiculeGps();
    EnginGpsLocation createEnginLocation(EnginGpsDTO dto);
    void deleteEngin(Long enginId);

}
