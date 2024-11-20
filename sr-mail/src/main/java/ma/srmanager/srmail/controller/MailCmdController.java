package ma.srmanager.srmail.controller;

import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.mail.MailSendDTO;
import ma.srmanager.srmail.services.MailCmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/mail")
public class MailCmdController {

    @Autowired
    private MailCmdService mailCmdService;

    @PostMapping(path = "/send")
    public SrResponseMessage sendMail(@RequestBody  MailSendDTO dto){
        return mailCmdService.send(dto.getTo(), dto.getSubject(), dto.getText());
    }



}
