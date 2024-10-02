package ma.srmanager.srjwt.controller.queries;

import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srjwt.dtos.AppUserResponseDTO;
import ma.srmanager.srjwt.entities.AppRole;
import ma.srmanager.srjwt.services.AccountQueryService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/jwt/queries")
public class JwtQueriesControler {

    private AccountQueryService accountQueryService;

    public JwtQueriesControler(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }


    @GetMapping(path = "/appRoles")
    public List<AppRole> appRoles() {
        return accountQueryService.appRoles();
    }

    @GetMapping(path = "/users")
    public List<AppUserResponseDTO> appUsers() {
        return accountQueryService.listUsers();
    }

    @GetMapping(path = "/allActivatedUsers")
    public List<AppUserResponseDTO> allActivatedUsers() {
        return accountQueryService.allActivatedUsers();
    }


    @GetMapping(path = "/profile")
    public AppUserResponseDTO profile(Principal principal) {
        return accountQueryService.profile(principal.getName());
    }

    @GetMapping(value = "/getUserByUserName/{username}")
    public AppUserResponseDTO getUserByUserName(@PathVariable(name = "username") String username) {
        return accountQueryService.getUserByUserName(username);
    }

    @GetMapping(value = "/getOneUseById/{id}")
    public AppUserResponseDTO getOneUseById(@PathVariable(name = "id") Long id) {
        return accountQueryService.getOneUseById(id);
    }

    @GetMapping(path = "/usersByRole/{role}")
    public List<AppUserResponseDTO> usersByRole(@PathVariable(name = "role") String role) {
        log.info("*********************************************************************");
        log.info("*********  usersByRole 1 ***************");
        log.info("*********  role ==>"+role);
        log.info("*********************************************************************");

        return accountQueryService.usersByRole(role);
    }



}

