package ma.srmanager.srnotification.services.queries;

import ma.srmanager.srnotification.entities.SrLog;

import java.util.List;

public interface LogQueriesService {
    List<SrLog> logsByUsername(String username);

    List<SrLog> logsByUserId(Long id);
}
