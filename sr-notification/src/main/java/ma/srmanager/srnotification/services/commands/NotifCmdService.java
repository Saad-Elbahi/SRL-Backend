package ma.srmanager.srnotification.services.commands;

import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.notification.CreateNotifRequestDTO;
import ma.srmanager.coreapi.notification.UpdateNotifReadStatusDTO;
import ma.srmanager.srnotification.entities.SrNotification;

import java.util.List;

public interface NotifCmdService {

    SrResponseMessage create(CreateNotifRequestDTO dto);

    SrNotification updateReadStatus(UpdateNotifReadStatusDTO dto);

    List<SrNotification> readAllNotifs(Long userId);
}
