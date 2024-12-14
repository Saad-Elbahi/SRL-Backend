package org.example.enginservice.service;

import org.example.enginservice.dto.EnginImputationRequestDTO;
import org.example.enginservice.entities.EnginImputation;

import java.io.IOException;
import java.util.List;

public interface EnginImputationService {
    List<EnginImputation> getAllImputations();

    List<EnginImputation> saveImputation(EnginImputationRequestDTO dto, String token) throws IOException;
    List<EnginImputation> updateImputation(EnginImputationRequestDTO dto, String token);

    void deleteImputation(Long id);

    List<EnginImputation> geImputationByEnginRouteId(Long vehiculeRouteId);
}
