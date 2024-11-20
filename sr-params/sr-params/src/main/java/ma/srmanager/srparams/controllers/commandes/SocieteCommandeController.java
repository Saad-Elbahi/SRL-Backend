package ma.srmanager.srparams.controllers.commandes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.params.dtos.CreateSocieteDTO;
import ma.srmanager.srparams.entities.Societe;
import ma.srmanager.srparams.services.commandes.SocieteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/societe/commands")
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
public class SocieteCommandeController {

    SocieteService societeService;

    @PostMapping("/create")
    public Societe create(@RequestBody CreateSocieteDTO dto) {
        return societeService.createSociete(dto);
    }

}
