package ma.srmanager.srparams.services.queries;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.enums.global.ParamType;
import ma.srmanager.srparams.entities.Param;
import ma.srmanager.srparams.repositories.ParamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ParamQueryServiceImpl implements ParamQueryService {

    private ParamRepository paramRepository;

    @Override
    public Param byId(Long id) {
        log.info("*********  ***************");
        log.info("ParamQueryServiceImpl ->" );
        log.info("Param ID ->" + id);
        log.info("************  ************");

        return paramRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Param introuvable"));
    }

    @Override
    public Param bySymbole(String symbole) {
        return paramRepository.findFirstBySymbole(symbole);
    }

    @Override
    public Param byTypeAndSymbole(String type, String symbole) {
        return paramRepository.findFirstByParamTypeAndSymbole(ParamType.valueOf(type),symbole);
    }

    @Override
    public Param byIntitule(String intitule) {
        return paramRepository.findFirstByIntitule(intitule);
    }

    @Override
    public Param byTypeAndIntitule(String type, String intitule) {
        return paramRepository.findFirstByParamTypeAndIntitule(ParamType.valueOf(type),intitule);
    }

    @Override
    public List<Param> byType(String type) {
        return paramRepository.findAllByParamType(ParamType.valueOf(type));
    }




}
