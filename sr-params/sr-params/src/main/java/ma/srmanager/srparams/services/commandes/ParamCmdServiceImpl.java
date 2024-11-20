package ma.srmanager.srparams.services.commandes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.params.dtos.CreateParamRequestDTO;
import ma.srmanager.coreapi.params.dtos.UpdateParamRequestDTO;
import ma.srmanager.srparams.entities.Param;
import ma.srmanager.srparams.repositories.ParamRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ParamCmdServiceImpl implements ParamCmdService {

    private ParamRepository paramRepository;

    @Override
    public Param create(CreateParamRequestDTO dto) {
        log.info("********* params-query ***************");
        log.info("ParamCreatedEvent ->Intitule   :" + dto.getIntitule() + "  received");
        log.info("ParamCreatedEvent -> ParamType.." + dto.getParamType() + "  received");
        log.info("************ params-query ************");

        Param param = paramRepository.findFirstByParamTypeAndIntitule(dto.getParamType(), dto.getIntitule());

        if (param == null) {
            param = new Param();
            param.setIntitule(dto.getIntitule());
            param.setSymbole(dto.getSymbole());
            param.setDescription(dto.getDescription());
            param.setString1(dto.getString1());
            param.setString2(dto.getString2());
            param.setString3(dto.getString3());
            param.setString4(dto.getString4());
            param.setString5(dto.getString5());
            param.setDefaultValue(dto.getDefaultValue());
            param.setParamType(dto.getParamType());
            paramRepository.save(param);
        }
        return param;
    }

    @Override
    public Param update(UpdateParamRequestDTO dto) {
        log.info("********** params-query **************");
        log.info("ParamUpdatedEvent ->\" + dto.getIntitule() +\" RECEVED");
        log.info("*********** params-query *************");

        Param param = new Param();
        param.setId(dto.getId());
        param.setIntitule(dto.getIntitule());
        param.setDescription(dto.getDescription());
        param.setString1(dto.getString1());
        param.setString2(dto.getString2());
        param.setString3(dto.getString3());
        param.setString4(dto.getString4());
        param.setString5(dto.getString5());
        return paramRepository.save(param);
    }

    @Override
    public String delete(Long id) {
        log.info("********** params-query **************");
        log.info("ParamDeletedEvent ->" + id + " RECEVED");
        log.info("*********** params-query *************");

        paramRepository.deleteById(id);
        return "Param deleted";
    }
}
