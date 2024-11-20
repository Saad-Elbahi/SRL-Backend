package ma.srmanager.srmouvementv.services;


import ma.srmanager.srmouvementv.models.SubContractor;

import java.io.IOException;
import java.util.List;

public interface SubContractorService {
    List<SubContractor> getAllSoustraitants(String token, Long projectId) throws IOException;

    SubContractor byId(Long id, String token) ;
}
