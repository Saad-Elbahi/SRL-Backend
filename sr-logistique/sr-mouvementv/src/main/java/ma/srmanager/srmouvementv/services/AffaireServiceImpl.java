package ma.srmanager.srmouvementv.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.model.Affaire;
import ma.srmanager.srmouvementv.model.Fournisseur;
import ma.srmanager.srmouvementv.repositories.AffaireRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class AffaireServiceImpl implements AffaireService {

    private AffaireRepository affaireRepository;

    private ObjectMapper objectMapper;

    private RestTemplate restTemplate;

    @Override
    public List<Affaire> getAllAffaire() {

        log.info("get All Affaires");
        List<Affaire> affaires = new ArrayList<>();
        try {
            affaires = affaireRepository.findAll();
            log.info("size Of Affaires ");
            log.info(String.valueOf(affaires.size()));
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return affaires;
    }

    @Transactional
    @Override
    public List<Affaire> saveAffaireFromApi() throws IOException {
        String apiUrl = "https://rouandigps.com/api/api.php?api=user&key=88918E46B26489F0ECDC7966541FE2A9&cmd=USER_GET_ZONES";
        // HTTP GET request to the API
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        // Parse JSON response
        JsonNode jsonData = objectMapper.readTree(jsonResponse);
        List<Affaire> newAffaires = new ArrayList<>();
        // map JSON data to Affaire objects
        for (JsonNode zoneData : jsonData) {
            String groupName = zoneData.get("group_name").asText();
            if ("CHANTIERS STE ROUANDI".equals(groupName)) {
                String name = zoneData.get("name").asText();
                // Check if an Affaire with the same name and groupName already exists
                Optional<Affaire> existingAffaire = affaireRepository.findByNameAndGroupName(name, groupName);
                if (!existingAffaire.isPresent()) {
                    Affaire affaire = new Affaire();
                    affaire.setGroupName(groupName);
                    affaire.setName(name);
                    newAffaires.add(affaire);
                }
            }
        }
        // Save new Affaire objects to the database
        affaireRepository.saveAll(newAffaires);
        return affaireRepository.findAll();

    }

    @Transactional
    @Override
    public Affaire getAffaireById(Long id) {

        return affaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement not found with id: " + id));
    }

    @Override
    public void deleteAffaire(Long id) {
        affaireRepository.deleteById(id);
    }
}
