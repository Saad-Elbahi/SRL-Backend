package ma.srmanager.srnotification.controllers.commands;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.log.CreateLogRequestDTO;
import ma.srmanager.srnotification.entities.SrLog;
import ma.srmanager.srnotification.services.commands.LogCmdService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RequestMapping("/log/commands")
public class LogCmdController {

    LogCmdService logCmdService;

    @PostMapping("/create")
    public SrLog create(@RequestBody CreateLogRequestDTO dto,
                        @RequestHeader(name = "Authorization") String token) {

        log.info("Create Log Request => Sart");

        dto.setDateCreation(LocalDate.now());
        dto.setTimeCreation(LocalTime.now());
        dto.setToken(token);
        return logCmdService.create(dto);

    }

    @GetMapping("/updateAll")
    public SrResponseMessage updateAll(@RequestHeader(name = "Authorization") String token) {


        return logCmdService.updateAll(token);

    }

}
