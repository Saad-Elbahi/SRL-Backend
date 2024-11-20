package ma.srmanager.srnotification.services.queries;

import ma.srmanager.srnotification.entities.SrNotification;

import java.util.List;


public interface NotifQueriesService {

    List<SrNotification> allNotifByUserId(Long userId);

    List<SrNotification> readNotifsByUserId(Long userId);

    List<SrNotification> unreadNotifsByUserId(Long userId);

    List<SrNotification> unreadNotifsByRole(String roleName);
}
