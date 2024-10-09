package ma.srmanager.srmouvementv.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.model.Fournisseur;
import ma.srmanager.srmouvementv.repositories.FournisseurRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;
    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    @Override
    public List<Fournisseur> getAllFrs() {
        log.info("get All Fournisseur");
        List<Fournisseur> frs = new ArrayList<>();
        try {
            frs = fournisseurRepository.findAll();
            log.info("size Of Fournisseurs ");
            log.info(String.valueOf(frs.size()));
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return frs;
    }

    @Override
    public List<Fournisseur> saveFournisseurFromApi() throws IOException {
        String apiUrl = "https://rouandigps.com/api/api.php?api=user&key=88918E46B26489F0ECDC7966541FE2A9&cmd=USER_GET_ZONES";
        //  HTTP GET request to the API
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        // Parse JSON response
        JsonNode jsonData = objectMapper.readTree(jsonResponse);
        List<Fournisseur> fournisseurs = new ArrayList<>();
        // map JSON data  to Fournisseur objects
        for (JsonNode fournisseurData : jsonData) {
            String groupName = fournisseurData.get("group_name").asText();
            if ("FOURNISSEUR".equals(groupName)) {
                String name = fournisseurData.get("name").asText();
                List<Fournisseur> existingFournisseur = fournisseurRepository.findByNameAndGroupName(name, groupName);
                if (existingFournisseur.isEmpty()) {
                    Fournisseur fournisseur = new Fournisseur();
                    fournisseur.setGroupName(fournisseurData.get("group_name").asText());
                    fournisseur.setName(fournisseurData.get("name").asText());
                    fournisseur.setVertices(fournisseurData.get("vertices").asText());
                    fournisseurs.add(fournisseur);
                }
            }
        }
        // Save Affaire  to the database
        fournisseurRepository.saveAll(fournisseurs);
        return fournisseurRepository.findAll();
    }

    @Override
    public Fournisseur getFournisseurById(Long fournisseurId) {
        return fournisseurRepository.findById(fournisseurId)
                .orElseThrow(() -> new RuntimeException("Fournisseur not found with id: " + fournisseurId));
    }

    @Override
    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
    }
}
