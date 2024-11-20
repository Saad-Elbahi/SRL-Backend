package ma.srmanager.sraffaires.controllers.queries.affaire;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.enums.marche.ProjetStatus;
import ma.srmanager.sraffaires.entities.imputation.Marche;
import ma.srmanager.sraffaires.services.queries.affaire.MarcheQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RequestMapping("/marches/queries")
public class MarcheQueryController {

    private MarcheQueryService marcheQueryService;

  /*  @GetMapping("/all")
    public List<Marche> all(@RequestHeader(name = "Authorization") String token) {
        return marcheQueryService.all(token);
    }
*/
    @GetMapping("/byStatus/{status}")
    public List<Marche> byStatus(@PathVariable String status,
                                 @RequestHeader(name = "Authorization") String token) {
        return marcheQueryService.byStatus(ProjetStatus.valueOf(status), token);
    }

    //marchePointageList
   /* @GetMapping("/marchePointageList")
    public List<Marche> marchePointageList() {
        return marcheQueryService.marchePointageList();
    }

    @GetMapping("/byStatusAndChefZone/{status}/{username}")
    public List<Marche> byStatusAndChefZone(@PathVariable String status, @PathVariable String username) {
        return marcheQueryService.byStatusAndChefZone(ProjetStatus.valueOf(status), username);
    }

    @GetMapping("/byStatusAndTechnicien/{status}/{username}")
    public List<Marche> byStatusAndTechnicien(@PathVariable String status, @PathVariable String username) {
        return marcheQueryService.byStatusAndTechnicien(ProjetStatus.valueOf(status), username);
    }
*/
    @GetMapping("/byId/{id}")
    public Marche marcheById(@PathVariable Long id) {
        log.info("  marcheById=> Start");
        log.info("ID = " + id);
        return marcheQueryService.marcheById(id);
    }


    @GetMapping("/byCode/{code}")
    public Optional<Marche> marcheByCode(@PathVariable String code) {
        return marcheQueryService.marcheByCode(code);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
