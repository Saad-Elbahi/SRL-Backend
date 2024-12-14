package org.example.enginservice.service;


import org.example.enginservice.dto.LotDTO;

import java.util.List;
import java.util.Optional;

public interface LotService {
    LotDTO createLot(LotDTO clientDTO);

    LotDTO updateLot(Long id, LotDTO clientDTO);

    List<LotDTO> getAllLots();

    Optional<LotDTO> getLotById(Long id);

    void deleteLot(Long id);
}
