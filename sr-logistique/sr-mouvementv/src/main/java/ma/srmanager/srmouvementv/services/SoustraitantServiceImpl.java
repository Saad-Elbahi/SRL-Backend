package ma.srmanager.srmouvementv.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.ClientDTO;
import ma.srmanager.srmouvementv.dto.LotDTO;
import ma.srmanager.srmouvementv.dto.SoustraitantDTO;
import ma.srmanager.srmouvementv.model.Lot;
import ma.srmanager.srmouvementv.model.Soustraitant;
import ma.srmanager.srmouvementv.repositories.SoustraitantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SoustraitantServiceImpl implements SoustraitantService {

    private final SoustraitantRepository soustraitantRepository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public SoustraitantServiceImpl(SoustraitantRepository soustraitantRepository, ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.soustraitantRepository = soustraitantRepository;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }
    private SoustraitantDTO convertToDto(Soustraitant soustraitant) {
        SoustraitantDTO dto = new SoustraitantDTO();
        dto.setId(soustraitant.getId());
        dto.setFullName(soustraitant.getFullName());
        return dto;
    }

    private Soustraitant convertToEntity(SoustraitantDTO dto) {
        Soustraitant soustraitant = new Soustraitant();
        soustraitant.setId(dto.getId());
        soustraitant.setFullName(dto.getFullName());
        return soustraitant;
    }

    public List<Soustraitant> getAllSoustraitants(String token) {
        String url = "https://node118212-env-sr-str.jcloud-ver-jpe.ik-server.com/subcontractors/queries/all";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            System.out.println("Sending request to external API...");

            ResponseEntity<Soustraitant[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Soustraitant[].class);

            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + Arrays.toString(response.getBody()));

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<Soustraitant> soustraitants = Arrays.asList(response.getBody());

                soustraitantRepository.saveAll(soustraitants);

                return soustraitants;
            } else {
                return Collections.emptyList();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<SoustraitantDTO> getAllSoustraitant() {
        return soustraitantRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}


