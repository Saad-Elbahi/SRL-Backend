package ma.srmanager.srparams.services.commandes;

import ma.srmanager.coreapi.params.dtos.CreateParamRequestDTO;
import ma.srmanager.coreapi.params.dtos.UpdateParamRequestDTO;
import ma.srmanager.srparams.entities.Param;

public interface ParamCmdService {

     Param create(CreateParamRequestDTO dto);

     Param update(UpdateParamRequestDTO dto);

     String delete(Long id);
}
