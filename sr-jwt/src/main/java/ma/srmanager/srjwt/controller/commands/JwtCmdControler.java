package ma.srmanager.srjwt.controller.commands;

import lombok.extern.slf4j.Slf4j;

import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.jwt.*;
import ma.srmanager.srjwt.entities.AppRole;
import ma.srmanager.srjwt.entities.AppUser;
import ma.srmanager.srjwt.services.AccountCmdService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/jwt/commands")
public class JwtCmdControler {

    private AccountCmdService accountCmdService;

    public JwtCmdControler(AccountCmdService accountCmdService) {
        this.accountCmdService = accountCmdService;
    }


    @PostMapping(path = "/createUser")
    @PostAuthorize("ADMIN")
    public AppUser createUser(@RequestBody CreateUserDTO createUserDTO,
                              @RequestHeader(name = "Authorization") String token) {
        log.info("*********  ***************");
        log.info("CreateUser 1 ");
        log.info("FirstName " + createUserDTO.getFirstName());
        log.info("LastName " + createUserDTO.getLastName());
        createUserDTO.setToken(token);
        return accountCmdService.createUser(createUserDTO);
    }


    @PostMapping(path = "/createRole")
    @PostAuthorize("ADMIN")
    public AppRole createRole(@RequestBody AppRole appRole) {
        return accountCmdService.addNewRole(appRole);
    }

    @GetMapping(path = "/initPwd/{username}")
    @PostAuthorize("ADMIN")
    public SrResponseMessage initPwd(@PathVariable(name = "username") String username,
                                     @RequestHeader(name = "Authorization") String token) {

        log.info("initPwd ->" + username);

        return accountCmdService.initPwd(username,token);
    }

    @PostMapping(path = "/turnOffOrOnUser")
    @PostAuthorize("ADMIN")
    public AppUser turnOffOrOnUser(@RequestBody TurnOffOrOnUserDTO dto) {

        log.info("********* Controller ***************");
        log.info("TurnOffOrOnUser ->" + dto.getUsername());
        log.info("************  ************");

        return accountCmdService.turnOffOrOnUser(dto);
    }


    @PostMapping(path = "/addRoleToUser")
    @PostAuthorize("ADMIN")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountCmdService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

    @GetMapping(path ="/refreshtoken")
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("refreshToken");
        accountCmdService.refreshToken(request,response);
    }

   /* @PostMapping(value = "/saveUser")
    public AppUser saveUser(@RequestBody AppUser user) {
        return accountService.saveUser(user);
    }
*/
    @PostMapping(value = "/updateInfosUser")
    public AppUser updateInfosUser(@RequestBody UpdateInfosUserDTO updateInfosUserDTO) {
        return accountCmdService.updateInfosUser(updateInfosUserDTO);
    }


    @PostMapping(value = "/updatePwdUser")
    public UpdatePwdUserResponseDTO updatePwdUser(@RequestBody UpdatePwdUserRequestDTO updatePwdUserRequestDTO) {
        return accountCmdService.updatePwdUser(updatePwdUserRequestDTO);
    }


    @GetMapping(value = "/backupDb")
    public String backupDB() {
        return accountCmdService.backupDB();
    }

    @GetMapping(value = "/initSecService")
    @PostAuthorize("ADMIN")
    public String initSecService() {
        return accountCmdService.initSecService();
    }


    @GetMapping(value = "/updateWorkingDays")
    @PostAuthorize("ADMIN")
    public SrResponseMessage updateWorkingDays() {
        return accountCmdService.updateWorkingDays();
    }




}

