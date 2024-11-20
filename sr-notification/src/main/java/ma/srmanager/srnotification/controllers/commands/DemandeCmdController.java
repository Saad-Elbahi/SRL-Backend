package ma.srmanager.srnotification.controllers.commands;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.notification.CreateNotifRequestDTO;
import ma.srmanager.coreapi.notification.UpdateNotifReadStatusDTO;
import ma.srmanager.srnotification.entities.SrNotification;
import ma.srmanager.srnotification.services.commands.NotifCmdService;
import ma.srmanager.srnotification.services.queries.NotifQueriesService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RequestMapping("/demande/commands")
public class DemandeCmdController {

    NotifCmdService notifCmdService;
    NotifQueriesService notifQueriesService;

    @PostMapping("/create")
    //@SendTo("/topic/hi")
    public SrResponseMessage create(@RequestBody CreateNotifRequestDTO dto,
                                    @RequestHeader(name = "Authorization") String token){
        dto.setDateCreation(LocalDate.now());
        dto.setTimeCreation(LocalTime.now());
        dto.setToken(token);
       return notifCmdService.create(dto);

    }

    @PostMapping("/updateReadStatus")
    public SrNotification updateReadStatus(@RequestBody UpdateNotifReadStatusDTO dto){
        return notifCmdService.updateReadStatus(dto);
    }

    @GetMapping("/readAllNotifs/{userId}")
    public List<SrNotification> readAllNotifs(@PathVariable Long userId){
        return notifCmdService.readAllNotifs(userId);
    }

}
