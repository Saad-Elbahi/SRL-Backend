package ma.srmanager.srnotification.services.queries;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.enums.notification.NotifReadStatus;
import ma.srmanager.srnotification.entities.SrNotification;
import ma.srmanager.srnotification.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class NotifQueriesServiceImpl implements NotifQueriesService {

    private NotificationRepository notificationRepository;

    @Override
    public List<SrNotification> allNotifByUserId(Long userId) {
        return notificationRepository.findAllByDestinationId(userId);
    }

    @Override
    public List<SrNotification> readNotifsByUserId(Long userId) {
        return notificationRepository.findAllByDestinationId(userId)
                .stream()
                .filter(notification -> notification.getNotifReadStatus().equals(NotifReadStatus.READ))
                .collect(Collectors.toList());
    }

    @Override
    public List<SrNotification> unreadNotifsByUserId(Long userId) {
        return notificationRepository.findAllByDestinationId(userId)
                .stream()
                .filter(notification -> notification.getNotifReadStatus().equals(NotifReadStatus.UNREAD))
                .collect(Collectors.toList());
    }

    @Override
    public List<SrNotification> unreadNotifsByRole(String roleName) {
        return notificationRepository.findAllByDestinationRole(roleName)
                .stream()
                .filter(notification -> notification.getNotifReadStatus().equals(NotifReadStatus.UNREAD))
                .collect(Collectors.toList());
    }
}
