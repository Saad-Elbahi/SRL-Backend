package ma.srmanager.srnotification.controllers.query;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srnotification.entities.SrNotification;
import ma.srmanager.srnotification.services.queries.NotifQueriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RequestMapping("/notif/queries")
public class NotifQueriesController {

    private NotifQueriesService notifQueriesService;

    @GetMapping("/allNotifByUserId/{userId}")
    public List<SrNotification> allNotifByUserId(@PathVariable(name = "userId") Long userId){
        return notifQueriesService.allNotifByUserId(userId);
    }

    @GetMapping("/readNotifsByUserId/{userId}")
    public List<SrNotification> readNotifsByUserId(@PathVariable(name = "userId") Long userId){
        return notifQueriesService.readNotifsByUserId(userId);
    }

    @GetMapping("/unreadNotifsByUserId/{userId}")
    public List<SrNotification> unreadNotifsByUserId(@PathVariable(name = "userId") Long userId){
        return notifQueriesService.unreadNotifsByUserId(userId);
    }

    @GetMapping("/unreadNotifsByRole/{roleName}")
    public List<SrNotification> unreadNotifsByRole(@PathVariable(name = "roleName") String roleName){
        return notifQueriesService.unreadNotifsByRole(roleName);
    }

}
