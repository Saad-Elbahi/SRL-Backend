package ma.srmanager.srmouvementv.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.AssociateChauffeurAndPriceDTO;
import ma.srmanager.srmouvementv.model.Chauffeur;
import ma.srmanager.srmouvementv.model.VehiculeGpsLocation;
import ma.srmanager.srmouvementv.repositories.ChauffeurRepository;
import ma.srmanager.srmouvementv.repositories.VehiculeGpsLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class VehiculeGpsLocationServiceImpl implements VehiculeGpsLocationService {
    //@Autowired
    // static final Logger log = LoggerFactory.getLogger(VehiculeGpsLocationServiceImpl.class);
    @Autowired
    private VehiculeGpsLocationRepository vehiculeGpsLocationRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ChauffeurRepository chauffeurRepository;

    private static final String gpsApiKey = "88918E46B26489F0ECDC7966541FE2A9";
    private static final String gpsBaseUrl = "https://rouandigps.com/api/api.php";

    private void configureObjectMapper() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(formatter) {
            @Override
            public LocalDateTime deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws java.io.IOException {
                try {
                    return super.deserialize(p, ctxt);
                } catch (Exception e) {
                    // Log the error or handle it as needed
                    return null; // Or provide a default value
                }
            }
        };

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, deserializer);

        objectMapper.registerModule(module);
    }

    @Transactional
    @Override
    public List<VehiculeGpsLocation> saveVehiculeFromApi() throws Exception {

        log.info("saveVehiculeFromApi 2");

        //String apiKey = "88918E46B26489F0ECDC7966541FE2A9";
        //String baseUrl = "https://rouandigps.com/api/api.php";


        String apiUrl = String.format("%s?api=user&key=%s&cmd=USER_GET_OBJECTS", gpsBaseUrl, gpsApiKey);

        log.info(apiUrl);
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        JsonNode jsonData = objectMapper.readTree(jsonResponse);
        List<VehiculeGpsLocation> vehiculeGpsLocations = new ArrayList<>();

        // Define the regex pattern
        Pattern pattern = Pattern.compile("^V-.+");

        for (JsonNode locationData : jsonData) {
            String groupName = locationData.get("group_name").asText();
            // Check if the group name matches the pattern
            Matcher matcher = pattern.matcher(groupName);
            if (matcher.find()) {
                String name = locationData.get("name").asText();
                // Check if a VehiculeGpsLocation with the same name and group name already exists
                List<VehiculeGpsLocation> existingLocation = vehiculeGpsLocationRepository.findByNameAndGroupName(name, groupName);
                if (existingLocation.isEmpty()) {
                    VehiculeGpsLocation vehiculeGpsLocation = objectMapper.convertValue(locationData, VehiculeGpsLocation.class);
                    vehiculeGpsLocations.add(vehiculeGpsLocation);
                }
            }
        }
        vehiculeGpsLocationRepository.saveAll(vehiculeGpsLocations);
        return vehiculeGpsLocationRepository.findAll();
    }

    @Override
    public VehiculeGpsLocation associateChauffeurAndPrice(AssociateChauffeurAndPriceDTO dto) {
        // Fetch VehiculeGpsLocation by vehiculeId
        VehiculeGpsLocation vehiculeGpsLocation = vehiculeGpsLocationRepository.findById(dto.getVehiculeId())
                .orElseThrow(() -> new EntityNotFoundException("VehiculeGpsLocation with ID " + dto.getVehiculeId() + " not found."));

        // Fetch Chauffeur by chauffeurId
        Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId())
                .orElseThrow(() -> new EntityNotFoundException("Chauffeur with ID " + dto.getChauffeurId() + " not found."));

        // Associate Chauffeur and update other fields
        vehiculeGpsLocation.setChauffeur(chauffeur);
        vehiculeGpsLocation.setCostPerKm(dto.getCostPerKm());
        vehiculeGpsLocation.setModel(dto.getModel());
        vehiculeGpsLocation.setName(dto.getName()); // Updating name field
        vehiculeGpsLocation.setDevice(dto.getDevice()); // Updating device

        // Save and return the updated entity
        return vehiculeGpsLocationRepository.save(vehiculeGpsLocation);
    }

    @Transactional
    @Override
    public VehiculeGpsLocation getVehiculeById(Long id) {

        return vehiculeGpsLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement not found with id: " + id));
    }

    @Override
    public List<VehiculeGpsLocation> getAllVehiculeGps() {
        return vehiculeGpsLocationRepository.findAll();
    }

    @Transactional
    @Override
    public VehiculeGpsLocation updateVehiculeGpsLocation(Long vehiculeId, VehiculeGpsLocation updatedVehiculeGpsLocation) {
        // Fetch the existing VehiculeGpsLocation from the database
        VehiculeGpsLocation existingVehiculeGpsLocation = vehiculeGpsLocationRepository.findById(vehiculeId)
                .orElseThrow(() -> new EntityNotFoundException("VehiculeGpsLocation with ID " + vehiculeId + " not found."));

        // Update fields of the existing VehiculeGpsLocation
        existingVehiculeGpsLocation.setGroupName(updatedVehiculeGpsLocation.getGroupName());
        existingVehiculeGpsLocation.setImei(updatedVehiculeGpsLocation.getImei());
        existingVehiculeGpsLocation.setName(updatedVehiculeGpsLocation.getName());
        existingVehiculeGpsLocation.setDevice(updatedVehiculeGpsLocation.getDevice());
        existingVehiculeGpsLocation.setModel(updatedVehiculeGpsLocation.getModel());
        existingVehiculeGpsLocation.setPlateNumber(updatedVehiculeGpsLocation.getPlateNumber());
        existingVehiculeGpsLocation.setCostPerKm(updatedVehiculeGpsLocation.getCostPerKm());

        // Update chauffeur if provided
        if (updatedVehiculeGpsLocation.getChauffeur() != null) {
            Chauffeur updatedChauffeur = chauffeurRepository.findById(updatedVehiculeGpsLocation.getChauffeur().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Chauffeur with ID " + updatedVehiculeGpsLocation.getChauffeur().getId() + " not found."));
            existingVehiculeGpsLocation.setChauffeur(updatedChauffeur);
        }

        // Save and return the updated VehiculeGpsLocation
        return vehiculeGpsLocationRepository.save(existingVehiculeGpsLocation);
    }


    @Override
    public void deleteVehicule(Long vehiculeId) {
        vehiculeGpsLocationRepository.deleteById(vehiculeId);
    }

}


