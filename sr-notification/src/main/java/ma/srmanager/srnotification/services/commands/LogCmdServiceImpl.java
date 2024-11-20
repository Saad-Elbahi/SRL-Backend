package ma.srmanager.srnotification.services.commands;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.affaire.exceptions.MarcheIntrouvableException;
import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.jwt.AppUserResponseDTO;
import ma.srmanager.coreapi.log.CreateLogRequestDTO;
import ma.srmanager.srnotification.entities.SrLog;
import ma.srmanager.srnotification.openfeign.AppUserQueryRestClient;
import ma.srmanager.srnotification.repositories.SrLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@AllArgsConstructor
public class LogCmdServiceImpl implements LogCmdService {

    private SrLogRepository srLogRepository;
    private AppUserQueryRestClient appUserQueryRestClient;

    @Override
    public SrLog create(CreateLogRequestDTO dto) {
        log.info("Create Log Request => 1");


        AppUserResponseDTO appUser = appUserQueryRestClient.profile(dto.getToken());
        if (appUser == null) {
            log.info("AppUser introuvable");
            throw new MarcheIntrouvableException("AppUser introuvable");
        }

        SrLog srLog = new SrLog();

        srLog.setProjetName(dto.getProjetName());
        srLog.setApiUrl(dto.getApiUrl());
        srLog.setClasseName(dto.getClasseName());
        srLog.setObjectId(dto.getObjectId());
        srLog.setEvent(dto.getEvent());

        srLog.setDateCreation(dto.getDateCreation());
        srLog.setTimeCreation(dto.getTimeCreation());

        srLog.setUserId(appUser.getId());
        srLog.setUsername(appUser.getUsername());
        srLog.setFirstName(appUser.getFirstName());
        srLog.setLastName(appUser.getLastName());
        srLog.setFullName(appUser.getFullName());

        srLogRepository.save(srLog);
        log.info("Create Log Request => 2");

        return srLog;
    }

    @Override
    public SrResponseMessage updateAll(String token) {
        log.info("update All Logs => Sart");

        AtomicInteger i = new AtomicInteger();
        List<SrLog> srLogs = srLogRepository.findAll();
        srLogs.forEach(srLog -> {
            try {
                i.getAndIncrement();

                AppUserResponseDTO appUser = appUserQueryRestClient.getUserByUserName(srLog.getUsername(),token);
                if (appUser == null) {
                    log.info("AppUser introuvable");
                    throw new MarcheIntrouvableException("AppUser introuvable");
                }
                srLog.setUserId(appUser.getId());
                srLog.setFirstName(appUser.getFirstName());
                srLog.setLastName(appUser.getLastName());
                srLog.setFullName(appUser.getFullName());
                srLogRepository.save(srLog);
                log.info("Log " + i + "/"+srLogs.size()+" Updated");
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        });
        log.info("update All Logs => End");

        return new SrResponseMessage(true, "All Logs Updated");
    }
}
