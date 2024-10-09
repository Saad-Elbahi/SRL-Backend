package ma.srmanager.srmouvementv.services;


import ma.srmanager.srmouvementv.model.Soustraitant;

import java.io.IOException;
import java.util.List;

public interface SoustraitantService {
    List<Soustraitant> getAllSoustraitants(String token) throws IOException;
}
