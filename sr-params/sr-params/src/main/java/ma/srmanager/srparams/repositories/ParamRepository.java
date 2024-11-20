package ma.srmanager.srparams.repositories;

import ma.srmanager.coreapi.enums.global.ParamType;
import ma.srmanager.srparams.entities.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParamRepository extends JpaRepository<Param,Long> {

    List<Param> findAllByParamType(ParamType paramType);

    Param findFirstByIntitule(String intitule);

    Param findFirstByParamTypeAndIntitule(ParamType paramType,String intitule);

    Param findFirstBySymbole(String symbole);

    Param findFirstByParamTypeAndSymbole(ParamType paramType,String symbole);
}
