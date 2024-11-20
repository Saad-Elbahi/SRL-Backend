package ma.srmanager.srnotification.repositories;

import ma.srmanager.srnotification.entities.SrLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SrLogRepository extends JpaRepository<SrLog,Long> {

    List<SrLog> findByUsername(String username);

    List<SrLog> findByUserId(Long id);

}
