package org.example.enginservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enginservice.dto.EnginImputationRequestDTO;
import org.example.enginservice.entities.Client;
import org.example.enginservice.entities.EnginImputation;
import org.example.enginservice.entities.EnginRoute;
import org.example.enginservice.entities.Lot;
import org.example.enginservice.models.Marche;
import org.example.enginservice.models.SubContractor;
import org.example.enginservice.repository.ClientRepository;
import org.example.enginservice.repository.EnginImputationRepository;
import org.example.enginservice.repository.EnginRouteRepository;
import org.example.enginservice.repository.LotRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EnginImputationServiceImpl implements EnginImputationService {
    private EnginImputationRepository enginImputationRepository;
    private EnginRouteRepository enginRouteRepository;
    private AffaireService affaireService;
    private ClientRepository clientRepository;
    private LotRepository lotRepository;
    private SubContractorService subContractorService;

    @Override
    public List<EnginImputation> getAllImputations() {
        return enginImputationRepository.findAll();
    }

    @Override
    public List<EnginImputation> saveImputation(EnginImputationRequestDTO dto, String token) throws IOException {
        log.debug("Processing EnginImputationRequestDTO: {}", dto);

        // Validate and fetch EnginRoute
        EnginRoute enginRoute = enginRouteRepository.findById(dto.getEnginRouteId())
                .orElseThrow(() -> new EntityNotFoundException("EnginRoute not found with ID: " + dto.getEnginRouteId()));
        log.debug("EnginRoute fetched successfully with ID: {}", dto.getEnginRouteId());

        // Initialize EnginImputation
        EnginImputation imputation = (dto.getId() != null)
                ? enginImputationRepository.findById(dto.getId()).orElse(new EnginImputation())
                : new EnginImputation();

        imputation.setEnginRoute(enginRoute);

        // Validate and fetch Marche (Affaire)
        Marche marche = affaireService.getAffaireById(dto.getAffaireId(), token);
        if (marche == null) {
            throw new EntityNotFoundException("Affaire not found with ID: " + dto.getAffaireId());
        }
        imputation.setAffaireId(dto.getAffaireId());
        imputation.setAffaireCode(marche.getCode());
        log.debug("Affaire fetched successfully with ID: {}", dto.getAffaireId());

        // Validate and fetch Client (if provided)
        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + dto.getClientId()));
            imputation.setClient(client);
            log.debug("Client fetched successfully with ID: {}", dto.getClientId());
        }

        // Validate and fetch Lot (if provided)
        if (dto.getLotId() != null) {
            Lot lot = lotRepository.findById(dto.getLotId())
                    .orElseThrow(() -> new EntityNotFoundException("Lot not found with ID: " + dto.getLotId()));
            imputation.setLot(lot);
            log.debug("Lot fetched successfully with ID: {}", dto.getLotId());
        }

        // Validate and fetch SubContractor (if provided)
        if (dto.getSubContractorId() != null) {
            SubContractor subContractor = subContractorService.byId(dto.getSubContractorId(), token);
            if (subContractor == null) {
                throw new EntityNotFoundException("SubContractor not found with ID: " + dto.getSubContractorId());
            }
            imputation.setSubContractorId(dto.getSubContractorId());
            imputation.setSubContractorFullName(subContractor.getFullName());
            log.debug("SubContractor fetched successfully with ID: {}", dto.getSubContractorId());
        }

        // Set other fields
        imputation.setNbrHImputation(dto.getNbrHImputation());
        imputation.setObservation(dto.getObservation());
        imputation.setCostImputation(dto.getCostImputation());

        // Save Imputation
        enginImputationRepository.save(imputation);
        log.debug("EnginImputation saved successfully with ID: {}", imputation.getId());

        // Return updated list of imputations for the given EnginRoute
        return geImputationByEnginRouteId(dto.getEnginRouteId());
    }

    @Override
    public List<EnginImputation> updateImputation(EnginImputationRequestDTO dto, String token) {
        EnginImputation imputation = enginImputationRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("EnginImputation not found with id: " + dto.getId()));
        Marche marche = affaireService.getAffaireById(dto.getAffaireId(), token);

        imputation.setAffaireId(dto.getAffaireId());
        imputation.setAffaireCode(marche.getCode());


        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found"));
            imputation.setClient(client);
        }

        if (dto.getLotId() != null) {
            Lot lot = lotRepository.findById(dto.getLotId())
                    .orElseThrow(() -> new EntityNotFoundException("Lot not found"));
            imputation.setLot(lot);
        }

        if (dto.getSubContractorId() != null) {
            SubContractor subContractor = subContractorService.byId(dto.getSubContractorId(), token);
            if (subContractor != null) {
                new EntityNotFoundException("Client not found");
            }
            imputation.setSubContractorId(dto.getSubContractorId());
            imputation.setSubContractorFullName(subContractor.getFullName());
        }


        imputation.setId(dto.getId());
        imputation.setNbrHImputation(dto.getNbrHImputation());
        imputation.setObservation(dto.getObservation());
        imputation.setCostImputation(dto.getCostImputation());

        imputation.calculateCostImputation();
        enginImputationRepository.save(imputation);

        return geImputationByEnginRouteId(dto.getEnginRouteId());
    }

    @Override
    public void deleteImputation(Long id) {
        enginImputationRepository.deleteById(id);
    }

    @Override
    public List<EnginImputation> geImputationByEnginRouteId(Long enginRouteId) {
        return enginImputationRepository.findByEnginRouteId(enginRouteId);
    }
}
