package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.LotDTO;

import java.util.List;
import java.util.Optional;

public interface LotService {
    LotDTO createLot(LotDTO clientDTO);

    LotDTO updateLot(Long id, LotDTO clientDTO);

    List<LotDTO> getAllLots();

    Optional<LotDTO> getLotById(Long id);

    void deleteLot(Long id);
}
