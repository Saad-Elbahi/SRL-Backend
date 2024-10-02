package ma.srmanager.srjwt.services;



import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.jwt.*;
import ma.srmanager.srjwt.entities.AppRole;
import ma.srmanager.srjwt.entities.AppUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AccountCmdService {

    AppUser addNewUser(AppUser appUser);

    AppRole addNewRole(AppRole appRole);

    void addRoleToUser(String username, String roleName);

    void revokeRoleFromUser(String username, String roleName);


    void createAllRoles();

    String initSecService();

    void grantPrivilegesForAdmin(String username);

    void grantPrivilegesForUser(String username);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;


    //AppUser saveUser(AppUser user);


    AppUser updateInfosUser(UpdateInfosUserDTO updateInfosUserDTO);

    UpdatePwdUserResponseDTO updatePwdUser(UpdatePwdUserRequestDTO updatePwdUserRequestDTO);

    AppUser turnOffOrOnUser(TurnOffOrOnUserDTO dto);

    SrResponseMessage initPwd(String username, String token);

    String initAllPwd();

    String backupDB();


    AppUser createUser(CreateUserDTO createUserDTO);

    SrResponseMessage updateWorkingDays();

    String generateRandomPassword(int len, int randNumOrigin, int randNumBound);
}
