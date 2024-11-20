package ma.srmanager.srnotification.services.queries;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srnotification.entities.SrLog;
import ma.srmanager.srnotification.repositories.SrLogRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class LogQueriesServiceImpl implements LogQueriesService {

    private SrLogRepository srLogRepository;

    @Override
    public List<SrLog> logsByUsername(String username) {
        return srLogRepository.findByUsername(username);
    }

    @Override
    public List<SrLog> logsByUserId(Long id) {
        return srLogRepository.findByUserId(id)
                .stream()
                .sorted(Comparator.comparingDouble(SrLog::getId).reversed())
                .collect(Collectors.toList());
    }
}
