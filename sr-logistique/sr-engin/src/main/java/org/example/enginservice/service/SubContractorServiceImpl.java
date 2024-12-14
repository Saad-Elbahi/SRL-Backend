package org.example.enginservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.enginservice.dto.SoustraitantDTO;
import org.example.enginservice.models.SubContractor;
import org.example.enginservice.openfeign.SunContractorQueryRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor

public class SubContractorServiceImpl implements SubContractorService {



    private final SunContractorQueryRestClient sunContractorQueryRestClient;



    private SoustraitantDTO convertToDto(SubContractor subContractor) {
        SoustraitantDTO dto = new SoustraitantDTO();
        dto.setId(subContractor.getId());
        dto.setFullName(subContractor.getFullName());
        return dto;
    }

    private SubContractor convertToEntity(SoustraitantDTO dto) {
        SubContractor subContractor = new SubContractor();
        subContractor.setId(dto.getId());
        subContractor.setFullName(dto.getFullName());
        return subContractor;
    }

    @Override
    public List<SubContractor> getAllSoustraitants(String token, Long projectId) {
        // Updated URL to fetch all subcontractors
        //String url = "https://node118212-env-sr-str.jcloud-ver-jpe.ik-server.com/subcontractors/queries/all?projectId=" + projectId;
        //String url =  "https://node118212-env-sr-str.jcloud-ver-jpe.ik-server.com/subcontractors/queries/byProject/" + projectId;
        //String url =  SrUtils.soustraitanceHost +"/subcontractors/queries/byProject/" + projectId;

        return sunContractorQueryRestClient.byProject(projectId, token);

        /*HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            System.out.println("Sending request to external API...");

            // Make the HTTP GET request to the external API
            ResponseEntity<SubContractor[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, SubContractor[].class);

            //System.out.println("Response Status Code: " + response.getStatusCode());
            //System.out.println("Response Body: " + Arrays.toString(response.getBody()));

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Arrays.stream(response.getBody())
                        .map(subContractor -> new SubContractor(
                                subContractor.getId(),
                                subContractor.getFullName()
                        ))
                        .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }*/
    }



    @Override
    public SubContractor byId(Long id, String token) {
        //String url = "https://node118212-env-sr-str.jcloud-ver-jpe.ik-server.com/subcontractors/queries/byId/"+id;
        //String url ="https://node118212-env-sr-str.jcloud-ver-jpe.ik-server.com/subcontractors/queries/byId/"+id;
        //String url = SrUtils.soustraitanceHost +"/subcontractors/queries/byId/"+id;

        return sunContractorQueryRestClient.byId(id, token).orElse(null);

        /*HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            System.out.println("Sending request to external API...");

            ResponseEntity<SubContractor> response = restTemplate.exchange(url, HttpMethod.GET, entity, SubContractor.class);

            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.toString());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {

                //soustraitantRepository.saveAll(soustraitants);

                return Optional.ofNullable(response.getBody());
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
    }


  /*  @Override
    public List<SoustraitantDTO> getAllSoustraitant() {
        return soustraitantRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }*/
}


