package ma.srmanager.srnotification.services.commands;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.affaire.exceptions.MarcheIntrouvableException;
import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.base.srtools.SrToolsService;
import ma.srmanager.coreapi.enums.notification.NotifReadStatus;
import ma.srmanager.coreapi.jwt.AppUserResponseDTO;
import ma.srmanager.coreapi.notification.CreateNotifRequestDTO;
import ma.srmanager.coreapi.notification.UpdateNotifReadStatusDTO;
import ma.srmanager.srnotification.entities.SrNotification;
import ma.srmanager.srnotification.openfeign.AppUserQueryRestClient;
import ma.srmanager.srnotification.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class NotifCmdServiceImpl implements NotifCmdService {

    private NotificationRepository notificationRepository;
    private SrToolsService srToolsService;
    private AppUserQueryRestClient appUserQueryRestClient;

    @Override
    public SrResponseMessage create(CreateNotifRequestDTO dto) {

        log.info("Create NotifICATION 1");
        log.info("ChampImputation = "+dto.getChampImputationId());
        log.info("TOKEN = "+dto.getToken());

        try {
            createNotifForUser(dto);
            return new SrResponseMessage(true,"Notif Created...");
        } catch (Exception e) {
            log.info(e.getMessage());
            return new SrResponseMessage(false,"Notif not Created...");
        }
    }

    private void createNotifForUser(CreateNotifRequestDTO dto) {
        log.info("Create NotifICATION 2");

        List<SrNotification> srNotifications=new ArrayList<>();

        AppUserResponseDTO appUserSource = appUserQueryRestClient.getOneUseById(dto.getUserSourceId(), dto.getToken());
        if (appUserSource == null) {
            log.info("User Source introuvable...");
            throw new MarcheIntrouvableException("User Source introuvable...");
        }

        log.info("Create NotifICATION 3");

        List<AppUserResponseDTO> appUserResponseDTOS = appUserQueryRestClient.usersByRole(dto.getRole(), dto.getToken());
        appUserResponseDTOS.forEach(appUserResponseDTO -> {

            log.info("appUserSource =>" + appUserSource.getFirstName());
            log.info("appUserResponse =>" + appUserResponseDTO.getFirstName());

            SrNotification notification = new SrNotification();
            notification.setDateCreation(dto.getDateCreation());
            notification.setTimeCreation(dto.getTimeCreation());
            notification.setMois(srToolsService.getMonth(dto.getDateCreation()));
            notification.setAnnee(srToolsService.getYear(dto.getDateCreation()));

            notification.setDestinationId(appUserResponseDTO.getId());
            notification.setDestinationUsername(appUserResponseDTO.getUsername());
            notification.setDestinationFullName(appUserResponseDTO.getFirstName() + " " + appUserResponseDTO.getLastName());

        /*if(appUser!=null) {
            notification.setDestinationId(appUser.getId());
            notification.setDestinationUsername(appUser.getUsername());
            notification.setDestinationFullName(appUser.getFirstName() + " " + appUser.getLastName());
        }else{
            notification.setDestinationId(0L);
            notification.setDestinationUsername("");
            notification.setDestinationFullName("");
        }*/
            notification.setDestinationRole(dto.getRole());

            notification.setSourceId(dto.getUserSourceId());
            notification.setSourceUsername(appUserSource.getUsername());
            notification.setSourceFullName(appUserSource.getFirstName() + ' ' + appUserSource.getLastName());
           // notification.setSourceRole(appUserSource.getRole());

            notification.setChampImputation(dto.getChampImputationId());
            //notification.setMarcheCode(dto.getMarcheCode());


            notification.setIcon(dto.getIcon());
            notification.setHeading(dto.getHeading());
            notification.setMessage(dto.getMessage());
            notification.setUrl(dto.getUrl());
            notification.setNotifImportanceLevel(dto.getNotifImportanceLevel());
            notification.setNotifReadStatus(NotifReadStatus.UNREAD);
            notificationRepository.save(notification);
            srNotifications.add(notification);
            log.info("Create NotifICATION 4 ");
            log.info("notification =>"+notification.getId());
            log.info("notification =>"+notification.getMessage());

        });
        log.info("Create NotifICATION 5 ");

    }

    @Override
    public SrNotification updateReadStatus(UpdateNotifReadStatusDTO dto) {
        SrNotification notification = notificationRepository.findById(dto.getId()).orElse(null);
        if (notification == null) {
            throw new RuntimeException("SrNotification introuvable");
        }
        notification.setNotifReadStatus(dto.getNotifReadStatus());
        return notificationRepository.save(notification);
    }

    @Override
    public List<SrNotification> readAllNotifs(Long userId) {
        List<SrNotification> notifications = notificationRepository.findAllByDestinationId(userId)
                .stream()
                .filter(notification -> notification.getNotifReadStatus().equals(NotifReadStatus.UNREAD))
                .collect(Collectors.toList());

        notifications.forEach(notification -> {
            notification.setNotifReadStatus(NotifReadStatus.READ);
            notificationRepository.save(notification);
        });

        return notifications;
    }
}
