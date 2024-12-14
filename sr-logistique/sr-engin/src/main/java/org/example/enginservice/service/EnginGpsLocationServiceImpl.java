package org.example.enginservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.example.enginservice.dto.EnginGpsDTO;
import org.example.enginservice.entities.Chauffeur;
import org.example.enginservice.entities.EnginGpsLocation;
import org.example.enginservice.repository.ChauffeurRepository;
import org.example.enginservice.repository.EnginGpsLocationRepository;
import org.example.enginservice.repository.EnginRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class EnginGpsLocationServiceImpl implements EnginGpsLocationService {

    @Autowired
    private EnginGpsLocationRepository enginGpsLocationRepository;

    @Autowired
    private EnginRouteRepository enginRouteRepository;

    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String apiKey = "88918E46B26489F0ECDC7966541FE2A9";
    private final String baseUrl = "https://rouandigps.com/api/api.php";

    public void saveEnginGpsLocationFromApi() throws Exception {
        String apiUrl = String.format("%s?api=user&key=%s&cmd=USER_GET_OBJECTS", baseUrl, apiKey);
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        JsonNode jsonData = objectMapper.readTree(jsonResponse);
        List<EnginGpsLocation> enginGpsLocations = new ArrayList<>();

        // Define the regex pattern
        Pattern pattern = Pattern.compile("^E-.+");

        for (JsonNode locationData : jsonData) {
            String groupName = locationData.get("group_name").asText();
            // Check if the group name matches the pattern
            Matcher matcher = pattern.matcher(groupName);
            if (matcher.find()) {
                EnginGpsLocation enginGpsLocation = objectMapper.convertValue(locationData, EnginGpsLocation.class);
                enginGpsLocations.add(enginGpsLocation);
            }
        }
        enginGpsLocationRepository.saveAll(enginGpsLocations);
    }

    @Override
    public EnginGpsLocation updateEnginGpsLocation(EnginGpsDTO dto) {

        EnginGpsLocation enginGpsLocation = enginGpsLocationRepository.findById(dto.getEnginId())
                .orElseThrow(() -> new EntityNotFoundException("EnginGpsLocation with ID " + dto.getEnginId() + " not found."));

        Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId())
                .orElseThrow(() -> new EntityNotFoundException("Chauffeur with ID " + dto.getChauffeurId() + " not found."));
        enginGpsLocation.setChauffeur(chauffeur);
        enginGpsLocation.setCoutH(dto.getCoutH());
        enginGpsLocation.setModel(dto.getModel());
        enginGpsLocation.setName(dto.getName());
        enginGpsLocation.setDevice(dto.getDevice());

        return enginGpsLocationRepository.save(enginGpsLocation);
    }

    @Override
    public List<EnginGpsLocation> getAllVehiculeGps() {
        return enginGpsLocationRepository.findAll();
    }

    @Override
    public EnginGpsLocation createEnginLocation(EnginGpsDTO dto) {
        EnginGpsLocation enginGpsLocation = new EnginGpsLocation();
        Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId())
                .orElseThrow(() -> new EntityNotFoundException("Chauffeur with ID " + dto.getChauffeurId() + " not found."));
        enginGpsLocation.setChauffeur(chauffeur);
        enginGpsLocation.setCoutH(dto.getCoutH());
        enginGpsLocation.setModel(dto.getModel());
        enginGpsLocation.setName(dto.getName());
        enginGpsLocation.setDevice(dto.getDevice());
        return enginGpsLocationRepository.save(enginGpsLocation);
    }

    @Override
    public void deleteEngin(Long enginId) {
        enginGpsLocationRepository.deleteById(enginId);
    }


}

