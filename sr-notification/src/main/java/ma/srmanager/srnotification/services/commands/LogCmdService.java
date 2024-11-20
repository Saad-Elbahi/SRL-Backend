package ma.srmanager.srnotification.services.commands;

import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.log.CreateLogRequestDTO;
import ma.srmanager.srnotification.entities.SrLog;

public interface LogCmdService {
    SrLog create(CreateLogRequestDTO dto);

    SrResponseMessage updateAll(String token);
}
