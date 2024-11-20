package ma.srmanager.srnotification.controllers.query;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srnotification.entities.SrLog;
import ma.srmanager.srnotification.services.queries.LogQueriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RequestMapping("/log/queries")
public class LogQueriesController {

    LogQueriesService logQueriesService;

    @GetMapping("/logsByUsername/{username}")
    public List<SrLog> logsByUsername(@PathVariable String username) {
        log.info("Logs byUsername");

        return logQueriesService.logsByUsername(username);

    }

    @GetMapping("/logsByUserId/{id}")
    public List<SrLog> logsByUserId(@PathVariable Long id) {
        log.info(" logs By UserId");

        return logQueriesService.logsByUserId(id);

    }
}
