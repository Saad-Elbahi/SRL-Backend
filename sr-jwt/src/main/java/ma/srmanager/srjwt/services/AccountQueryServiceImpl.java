package ma.srmanager.srjwt.services;

import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srjwt.dtos.AppUserResponseDTO;
import ma.srmanager.srjwt.entities.AppRole;
import ma.srmanager.srjwt.entities.AppUser;
import ma.srmanager.srjwt.mappers.AppUserMapper;
import ma.srmanager.srjwt.repositories.AppRoleRepository;
import ma.srmanager.srjwt.repositories.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class AccountQueryServiceImpl implements AccountQueryService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private AppUserMapper appUserMapper;

    public AccountQueryServiceImpl(AppUserRepository appUserRepository,
                                   AppRoleRepository appRoleRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.appUserMapper = appUserMapper;
    }

    @Override
    public AppUserResponseDTO getUserByUserName(String username) {

        AppUserResponseDTO appUserResponseDTO = appUserMapper.entityToResponseDto(appUserRepository.findByUsername(username));
        appUserResponseDTO.setDate(new Date());
        return appUserResponseDTO;
    }

    @Override
    public List<AppUserResponseDTO> listUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(appUserMapper::entityToResponseDto)
                .collect(Collectors.toList());

    }

    @Override
    public long getCompte(Principal principal) {
        System.out.println(principal);
        return 0;
    }

    @Override
    public AppUserResponseDTO getOneUseById(Long id) {
        AppUserResponseDTO appUserResponseDTO = appUserMapper.entityToResponseDto(appUserRepository.findFirstById(id));
        appUserResponseDTO.setDate(new Date());
        return appUserResponseDTO;
    }

    @Override
    public List<AppRole> appRoles() {
        return appRoleRepository.findAll();
    }

    @Override
    public List<AppUserResponseDTO> usersByRole(String role) {

        //AppRole appRole = appRoleRepository.findByRoleName(role).orElse(null);
        log.info("*********************************************************************");
        log.info("*********  usersByRole 2 ***************");
        log.info("*********  Role ==>" + role);
        log.info("*********************************************************************");

        List<AppUserResponseDTO> dtos = appUserRepository.findAll()
                .stream()
                .filter(appUser -> appUser.getAppRoles()
                        .stream()
                        .filter(appRole -> appRole.getRoleName().equals(role))
                        .collect(Collectors.toList())
                        .size() > 0)
                .map(appUserMapper::entityToResponseDto)
                .collect(Collectors.toList());

        log.info("*********************************************************************");
        log.info("*********  usersByRole 3 ***************");
        log.info("*********  List<AppUser> ==>" + dtos);
        log.info("*********************************************************************");

        return dtos;
    }

    @Override
    public AppUserResponseDTO profile(String name) {
        log.info("profile");
        try {
            AppUser appUser = appUserRepository.findByUsername(name);
            AppUserResponseDTO appUserResponseDTO = appUserMapper.entityToResponseDto(appUser);

            appUserResponseDTO.setDate(new Date());
            if (appUserResponseDTO.getWorkingDays() != null) {
                List<String> myList = new ArrayList(Arrays.asList(appUserResponseDTO.getWorkingDays().split(";")));
                Integer day = LocalDate.now().getDayOfMonth();

                if (myList != null) {
                    if (myList.contains(String.valueOf(day))) {
                        appUserResponseDTO.setSessionExpired(false);
                        log.info("Session non expirée");
                    } else {
                        log.info("Session expirée");
                        appUserResponseDTO.setSessionExpired(true);
                    }
                }
            } else {
                appUserResponseDTO.setSessionExpired(false);
                log.info("Session non expirée");
            }

            return appUserResponseDTO;
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public List<AppUserResponseDTO> allActivatedUsers() {
        return appUserRepository.findAll()
                .stream()
                .filter(appUser -> appUser.isActived()
                        && appUser.isAccountNonLocked()
                        && appUser.isAccountNonExpired()
                        && appUser.isCredentialsNonExpired())
                .map(appUserMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }


}
