package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.LotDTO;
import ma.srmanager.srmouvementv.entities.Lot;
import ma.srmanager.srmouvementv.repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotServiceImpl implements LotService {
    @Autowired
    private LotRepository lotRepository;

    private LotDTO convertToDto(Lot lot) {
        LotDTO dto = new LotDTO();
        dto.setId(lot.getId());
        dto.setName(lot.getName());
        return dto;
    }

    private Lot convertToEntity(LotDTO dto) {
        Lot lot = new Lot();
        lot.setId(dto.getId());
        lot.setName(dto.getName());
        return lot;
    }

    @Override
    public LotDTO createLot(LotDTO lotDTO) {
        Lot lot = convertToEntity(lotDTO);
        Lot savedLot = lotRepository.save(lot);
        return convertToDto(savedLot);
    }

    @Override
    public LotDTO updateLot(Long id, LotDTO lotDTO) {
        Optional<Lot> lotOpt = lotRepository.findById(id);
        if (lotOpt.isPresent()) {
            Lot lot = lotOpt.get();
            lot.setName(lotDTO.getName());
            return convertToDto(lotRepository.save(lot));
        }
        return null; // Or throw an exception
    }

    @Override
    public List<LotDTO> getAllLots() {
        return lotRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LotDTO> getLotById(Long id) {
        return lotRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public void deleteLot(Long id) {
        lotRepository.deleteById(id);
    }
}
