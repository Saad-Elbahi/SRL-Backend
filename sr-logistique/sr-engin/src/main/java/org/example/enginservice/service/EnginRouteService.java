package org.example.enginservice.service;

import org.example.enginservice.dto.EnginRouteDTO;
import org.example.enginservice.entities.EnginRoute;

import java.time.LocalDate;
import java.util.List;

public interface EnginRouteService {
    List<EnginRoute> getAllEnginRoutes() ;
    void fetchAndSaveAllObjectRoutes(LocalDate startDate, LocalDate endDate);
     EnginRoute updateRendement(EnginRouteDTO dto);
}
