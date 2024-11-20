package ma.srmanager.srmouvementv.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.enums.marche.ProjetStatus;
import ma.srmanager.srmouvementv.models.Marche;
import ma.srmanager.srmouvementv.openfeign.MarcheQueryRestClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class AffaireServiceImpl implements AffaireService {


    private ObjectMapper objectMapper;

    private RestTemplate restTemplate;
    private MarcheQueryRestClient marcheQueryRestClient;

//    @Override
//    public List<Affaire> getAllAffaire() {
//
//        log.info("get All Affaires");
//        List<Affaire> affaires = new ArrayList<>();
//        try {
//            affaires = affaireRepository.findAll();
//            log.info("size Of Affaires ");
//            log.info(String.valueOf(affaires.size()));
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
//
//        return affaires;
//    }

    @Override
    public List<Marche> getALLAffaire(String token) throws IOException {
        //String url = "https://sr-affaires.jcloud-ver-jpe.ik-server.com/marches/queries/byStatus/EN_COURS";
        //String url =  "https://sr-affaires.jcloud-ver-jpe.ik-server.com/marches/queries/byStatus/EN_COURS";
        return marcheQueryRestClient.byStatus(ProjetStatus.EN_COURS.name(), token);

        /*HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            System.out.println("Sending request to external API...");

            ResponseEntity<Marche[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Marche[].class);

            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + Arrays.toString(response.getBody()));

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<Marche> marches = Arrays.asList(response.getBody());

                //affaireRepository.saveAll(affaires);

                return marches;
            } else {
                return Collections.emptyList();
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            return Collections.emptyList();
        }*/
    }

    @Override
    public Marche getAffaireById(Long id, String token)  {
        //String url = "https://sr-affaires.jcloud-ver-jpe.ik-server.com/marches/queries/byId/"+id;
        //String url = "https://sr-affaires.jcloud-ver-jpe.ik-server.com/marches/queries/byId/"+id;
        return marcheQueryRestClient.byId(id, token).orElse(null);

        /*HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            System.out.println("Sending request to external API...");

            ResponseEntity<Marche> response = restTemplate.exchange(url, HttpMethod.GET, entity, Marche.class);

            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.toString());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {

                //affaireRepository.saveAll(affaires);

                return response.getBody();
            } else {
                return null;
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }*/
    }
//    @Transactional
//    @Override
//    public List<Affaire> saveAffaireFromApi() throws IOException {
//        String apiUrl = "https://rouandigps.com/api/api.php?api=user&key=88918E46B26489F0ECDC7966541FE2A9&cmd=USER_GET_ZONES";
//        // HTTP GET request to the API
//        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
//        // Parse JSON response
//        JsonNode jsonData = objectMapper.readTree(jsonResponse);
//        List<Affaire> newAffaires = new ArrayList<>();
//        // map JSON data to Affaire objects
//        for (JsonNode zoneData : jsonData) {
//            String groupName = zoneData.get("group_name").asText();
//            if ("CHANTIERS STE ROUANDI".equals(groupName)) {
//                String name = zoneData.get("name").asText();
//                // Check if an Affaire with the same name and groupName already exists
//                Optional<Affaire> existingAffaire = affaireRepository.findByNameAndGroupName(name, groupName);
//                if (!existingAffaire.isPresent()) {
//                    Affaire affaire = new Affaire();
//                    affaire.setGroupName(groupName);
//                    affaire.setName(name);
//                    newAffaires.add(affaire);
//                }
//            }
//        }
//        // Save new Affaire objects to the database
//        affaireRepository.saveAll(newAffaires);
//        return affaireRepository.findAll();
//
//    }

//    @Transactional
//    @Override
//    public Affaire getAffaireById(Long id) {
//
//        return affaireRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Mouvement not found with id: " + id));
//    }
//
//    @Override
//    public void deleteAffaire(Long id) {
//        affaireRepository.deleteById(id);
//    }
}
