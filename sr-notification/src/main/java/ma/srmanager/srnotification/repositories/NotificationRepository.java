package ma.srmanager.srnotification.repositories;

import ma.srmanager.srnotification.entities.SrNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<SrNotification,Long> {

    List<SrNotification> findAllByDestinationId(Long destinationId);

    List<SrNotification> findAllByDestinationRole(String destinationRole);
}
