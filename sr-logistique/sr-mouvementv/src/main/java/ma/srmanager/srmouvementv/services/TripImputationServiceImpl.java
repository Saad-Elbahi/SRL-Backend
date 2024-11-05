package ma.srmanager.srmouvementv.services;

import lombok.AllArgsConstructor;
import ma.srmanager.srmouvementv.dto.TripImputationRequestDTO;
import ma.srmanager.srmouvementv.model.Client;
import ma.srmanager.srmouvementv.model.Lot;
import ma.srmanager.srmouvementv.model.TripImputation;
import ma.srmanager.srmouvementv.model.VehiculeRoute;
import ma.srmanager.srmouvementv.models.Affaire;
import ma.srmanager.srmouvementv.models.SubContractor;
import ma.srmanager.srmouvementv.repositories.ClientRepository;
import ma.srmanager.srmouvementv.repositories.LotRepository;
import ma.srmanager.srmouvementv.repositories.TripImputationRepository;
import ma.srmanager.srmouvementv.repositories.VehiculeRouteRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TripImputationServiceImpl implements TripImputationService {

    private TripImputationRepository tripImputationRepository;
    private VehiculeRouteRepository vehiculeRouteRepository;
    private AffaireService affaireService;
    private ClientRepository clientRepository;
    private LotRepository lotRepository;
    private SubContractorService subContractorService;

    @Override
    public TripImputation saveImputation(TripImputation imputation) {
        return tripImputationRepository.save(imputation);
    }

    @Override
    public List<TripImputation> getAllImputations() {
        return tripImputationRepository.findAll();
    }

    @Override
    public TripImputation getImputationById(Long id) {
        return tripImputationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteImputation(Long id) {
        tripImputationRepository.deleteById(id);
    }

    //service Association Imputation
    @Transactional
    @Override
    public List<TripImputation> saveImputation(TripImputationRequestDTO dto, String token) throws IOException {

        VehiculeRoute vehiculeRoute = vehiculeRouteRepository.findById(dto.getVehiculeRouteId())
                .orElseThrow(() -> new EntityNotFoundException("VehiculeRoute not found"));


        TripImputation imputation = new TripImputation();
        imputation.setVehiculeRoute(vehiculeRoute);

        Affaire affaire = affaireService.getAffaireById(dto.getAffaireId(), token);

        imputation.setAffaireId(dto.getAffaireId());
        imputation.setAffaireCode(affaire.getCode());


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

        SubContractor subContractor = subContractorService.byId(dto.getSubContractorId(), token);

        imputation.setSubContractorId(dto.getSubContractorId());
        imputation.setSubContractorFullName(subContractor.getFullName());
        imputation.setId(dto.getId());
        imputation.setFillingPercentage(dto.getFillingPercentage());
        imputation.setObservation(dto.getObservation());
        imputation.setCostImputation(dto.getCostImputation());

        tripImputationRepository.save(imputation);

        return geImputationByVehiculeRouteId(dto.getVehiculeRouteId());
        //return vehiculeRouteRepository.save(vehiculeRoute);
    }


    public List<TripImputation> updateImputation(TripImputationRequestDTO dto, String token) {

        TripImputation imputation = tripImputationRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("TripImputation not found with id: " + dto.getId()));

        // Update the fields
        Affaire affaire = affaireService.getAffaireById(dto.getAffaireId(), token);

        imputation.setAffaireId(dto.getAffaireId());
        imputation.setAffaireCode(affaire.getCode());


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

        SubContractor subContractor = subContractorService.byId(dto.getSubContractorId(), token);

        imputation.setSubContractorId(dto.getSubContractorId());
        imputation.setSubContractorFullName(subContractor.getFullName());
        imputation.setId(dto.getId());
        imputation.setFillingPercentage(dto.getFillingPercentage());
        imputation.setObservation(dto.getObservation());
        imputation.setCostImputation(dto.getCostImputation());

        // Recalculate the cost imputation
        imputation.calculateCostImputation();

        // Save the updated imputation
        tripImputationRepository.save(imputation);
        return geImputationByVehiculeRouteId(dto.getVehiculeRouteId());

    }

    @Override
    public List<TripImputation> geImputationByVehiculeRouteId(Long vehiculeRouteId) {
        return tripImputationRepository.findByVehiculeRouteId(vehiculeRouteId);
    }
//kpi


}
