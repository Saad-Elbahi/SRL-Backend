package ma.srmanager.srjwt.services;

import ma.srmanager.srjwt.dtos.AppUserResponseDTO;
import ma.srmanager.srjwt.entities.AppRole;

import java.security.Principal;
import java.util.List;

public interface AccountQueryService {

    AppUserResponseDTO getUserByUserName(String username);

    List<AppUserResponseDTO> listUsers();

    long getCompte(Principal principal);

    AppUserResponseDTO getOneUseById(Long id);

    List<AppRole> appRoles();

    List<AppUserResponseDTO> usersByRole(String role);

    AppUserResponseDTO profile(String name);

    List<AppUserResponseDTO> allActivatedUsers();


}
