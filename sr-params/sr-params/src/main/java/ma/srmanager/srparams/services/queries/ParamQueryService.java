package ma.srmanager.srparams.services.queries;

import ma.srmanager.srparams.entities.Param;

import java.util.List;

public interface ParamQueryService {

    Param byId(Long id);

    List<Param> byType(String type);

    Param byIntitule(String type);

    Param bySymbole(String type);

    Param byTypeAndSymbole( String type, String symbole);

    Param byTypeAndIntitule(String type,String intitule);


}
