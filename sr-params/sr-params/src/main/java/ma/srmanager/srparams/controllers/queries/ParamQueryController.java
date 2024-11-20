package ma.srmanager.srparams.controllers.queries;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srparams.entities.Param;
import ma.srmanager.srparams.services.queries.ParamQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RequestMapping("/params/queries")
public class ParamQueryController {

    private ParamQueryService paramQueryService;

    @GetMapping("/byType/{type}")
    public List<Param> params(@PathVariable String type) {
        return paramQueryService.byType(type);
    }

    @GetMapping("/byId/{id}")
    public Param paramById(@PathVariable Long id) {
        log.info("**************  /params/queries *****************");
        log.info("******  Get Param by id= " + id);
        log.info("*******************************");
        return paramQueryService.byId(id);
    }

    @GetMapping("/byIntitule/{intitule}")
    public Param byIntitule(@PathVariable String intitule) {
        return paramQueryService.byIntitule(intitule);
    }

    @GetMapping("/byTypeAndIntitule/{type}/{intitule}")
    public Param byTypeAndIntitule(@PathVariable String type, @PathVariable String intitule) {
        return paramQueryService.byTypeAndIntitule(type, intitule);
    }

    @GetMapping("/bySymbole/{symbole}")
    public Param bySymbole(@PathVariable String symbole) {
        return paramQueryService.bySymbole(symbole);
    }

    @GetMapping("/byTypeAndSymbole/{type}/{symbole}")
    public Param byTypeAndSymbole(@PathVariable String type, @PathVariable String symbole) {
        return paramQueryService.byTypeAndSymbole(type, symbole);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
