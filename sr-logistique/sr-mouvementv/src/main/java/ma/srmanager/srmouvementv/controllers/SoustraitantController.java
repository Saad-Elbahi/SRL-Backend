package ma.srmanager.srmouvementv.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.model.Soustraitant;
import ma.srmanager.srmouvementv.services.SoustraitantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/soustraitantapi")
public class SoustraitantController {
    @Autowired
    private SoustraitantService soustraitantService;

    @GetMapping("/soustraitants")
    public List<Soustraitant> getSoustraitants(@RequestHeader (name="Authorization") String token) throws IOException {
        log.info(token);
        return soustraitantService.getAllSoustraitants(token);
    }

}
