package ma.srmanager.srjwt.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.base.SrResponseMessage;
import ma.srmanager.coreapi.base.SrUtils;
import ma.srmanager.coreapi.jwt.*;
import ma.srmanager.coreapi.mail.MailSendDTO;
import ma.srmanager.srjwt.entities.AppRole;
import ma.srmanager.srjwt.entities.AppUser;
import ma.srmanager.srjwt.repositories.AppRoleRepository;
import ma.srmanager.srjwt.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class AccountCmdServiceImpl implements AccountCmdService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountCmdServiceImpl(AppUserRepository appUserRepository,
                                 AppRoleRepository appRoleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AppUser addNewUser(AppUser appUser) {
        AppUser appUser1 = appUserRepository.findByUsername(appUser.getUsername());
        if (appUser1 != null)
            return appUser1;
        else {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            return appUserRepository.save(appUser);
        }
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            log.info("The username [" + username + "] is not exists");
            throw new RuntimeException("The username [" + username + "] is not exists");
        }
        AppRole appRole = appRoleRepository.findByRoleName(roleName).orElse(null);
        if (appRole == null) {
            log.info("The role [" + roleName + "] is not exists");
            throw new RuntimeException("The role [" + roleName + "] is not exists");
        }

        if (appUser.getAppRoles() != null && !appUser.getAppRoles().contains(appRole)) {
            log.info("Approle add" + appRole.getRoleName());
            appUser.getAppRoles().add(appRole);
        }
    }

    @Override
    public void revokeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName).orElse(null);
        appUser.getAppRoles().remove(appRole);
    }


    @Override
    public String initSecService() {


        appUserRepository.findAll().forEach(appUser -> {
            appUser.getAppRoles().clear();
        });

        appRoleRepository.deleteAll();

        appUserRepository.deleteAll();

        createAllRoles();

        createAllUsers();


        //grantPrivilegesForAdmin("admin");


        return "Init ma.srmanager.srmail.security service is complted";
    }


    @Override
    public void createAllRoles() {

        addNewRole(new AppRole(null, ConstRoleName.USER, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.ADMIN, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.PDG, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.DGA, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.DAF, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.CDG, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.AUDIT, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.JUR, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.DIR_TECH, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.RESP_METREE, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.METREUR, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.RESP_COMPTA, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.ASSIST_COMPTA, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.RESP_INFO, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.ASSIST_INFO, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.ASSIST_LOG, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.RESP_LOG, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.ASSIST_MARCHE, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.RESP_MARCHE, TypeRole.ADMINISTARTION));


        addNewRole(new AppRole(null, ConstRoleName.RESP_RH, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.ASSIST_RH, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.RESP_ST, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.ASSIST_ST, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.RESP_ACHAT, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.ASSIST_ACHAT, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.RESP_CAISSE, TypeRole.ADMINISTARTION));
        addNewRole(new AppRole(null, ConstRoleName.CAISSIER, TypeRole.ADMINISTARTION));

        addNewRole(new AppRole(null, ConstRoleName.CHEF_ZONE, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.DIR_PROJET, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.ING_CH, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.COND_CH, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.TECH_CH, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.CHEF_CH, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.COMPT_CH, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.POINTEUR, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.GARDIENT_CH_JOUR, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.GARDIENT_CH_NUIT, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.MAGASINIER_CH, TypeRole.CHANTIER));

        addNewRole(new AppRole(null, ConstRoleName.RESP_DEPOT, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.TOPOGRAPHE, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.CAPORAL, TypeRole.CHANTIER));
        addNewRole(new AppRole(null, ConstRoleName.TRACEUR, TypeRole.CHANTIER));


    }

    private void createAllUsers() {


    }

    @Override
    public void grantPrivilegesForAdmin(String username) {
        addRoleToUser(username, ConstRoleName.ADMIN);
        addRoleToUser(username, ConstRoleName.USER);
    }

    @Override
    public void grantPrivilegesForUser(String username) {
        addRoleToUser(username, ConstRoleName.USER);
    }


    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("refreshToken");
        String authToken = request.getHeader(JWTUtil.JWT_HEADER_NAME);
        if (authToken != null && authToken.startsWith(JWTUtil.JWT_HEADER_PREFIX)) {
            try {
                String jwtRefreshToken = authToken.substring(JWTUtil.JWT_HEADER_PREFIX.length());

                Algorithm algo1 = Algorithm.HMAC256(JWTUtil.JWT_SECRET);

                JWTVerifier jwtVerifier = JWT.require(algo1).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);

                String username = decodedJWT.getSubject();
                AppUser appUser = appUserRepository.findByUsername(username);

                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim(JWTUtil.JWT_CLAIMS_ROLES, appUser.getAppRoles()
                                .stream()
                                .map(role -> role.getRoleName()).collect(Collectors.toList()))
                        .sign(algo1);

                /* jwtRefreshToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_REFRESH_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algo1);*/

                /*
                 .withClaim(JWTUtil.JWT_CLAIMS_COMPTE, appUser.getAppRoles().stream()
                                .filter(role -> role.getRoleName().startsWith(JWTUtil.JWT_PREFEX_COMPTE))
                                .map(role -> role.getRoleName().substring(null)
                                .findFirst().orElseGet(null))
                 */

                Map<String, String> idToken = new HashMap<>();
                idToken.put(JWTUtil.JWT_LIBELLE_ACCESS_TOKEN, jwtAccessToken);
                idToken.put(JWTUtil.JWT_LIBELLE_REFRESH_TOKEN, jwtRefreshToken);
                response.setContentType(JWTUtil.JWT_CONTENT_TYPE);
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh token required...!!!");
        }
    }


    @Override
    public AppUser updateInfosUser(UpdateInfosUserDTO updateInfosUserDTO) {

        AppUser appUser = appUserRepository.findByUsername(updateInfosUserDTO.getUsername());
        if (appUser == null)
            throw new RuntimeException("The username is not exists");

        appUser.setFirstName(updateInfosUserDTO.getFirstName());
        appUser.setLastName(updateInfosUserDTO.getLastName());
        appUser.setFullName(updateInfosUserDTO.getFirstName() + ' ' + updateInfosUserDTO.getLastName());
        appUser.setEmail(updateInfosUserDTO.getEmail());
        appUser.setAvatar(updateInfosUserDTO.getAvatar());
        appUser.setAccountNonLocked(updateInfosUserDTO.getAccountNonLocked());
        appUser.setAccountNonExpired(updateInfosUserDTO.getAccountNonExpired());
        appUser.setCredentialsNonExpired(updateInfosUserDTO.getCredentialsNonExpired());

        appUserRepository.save(appUser);

        appUser.getAppRoles().clear();

        if (!updateInfosUserDTO.getRoles().isEmpty()) {
            updateInfosUserDTO.getRoles().forEach(roleName -> {
                addRoleToUser(updateInfosUserDTO.getUsername(), roleName);
            });
        }
        if (!appUser.getAppRoles().isEmpty()) {
            appUser.setRole(appUser.getAppRoles().get(0).getRoleName());
            appUserRepository.save(appUser);
        }
        return appUser;
    }

    @Override
    public UpdatePwdUserResponseDTO updatePwdUser(UpdatePwdUserRequestDTO updatePwdUserRequestDTO) {

        UpdatePwdUserResponseDTO updatePwdUserResponseDTO = new UpdatePwdUserResponseDTO();

        AppUser appUser = appUserRepository.findByUsername(updatePwdUserRequestDTO.getUsername());

        if (appUser == null) throw new RuntimeException("The username is not exists");

       /* UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(updatePwdUserDTO.getUsername(), updatePwdUserDTO.getOldPassword());

        Authentication authentication=authenticationManager.authenticate(authenticationToken);*/

        if (passwordEncoder.matches(updatePwdUserRequestDTO.getOldPassword(), appUser.getPassword())) {
            appUser.setPassword(passwordEncoder.encode(updatePwdUserRequestDTO.getNewPassword()));
            appUserRepository.save(appUser);

            updatePwdUserResponseDTO.setResult(true);
            updatePwdUserResponseDTO.setMessage("Mot de passe modifié avec succés");
        } else {
            updatePwdUserResponseDTO.setResult(false);
            updatePwdUserResponseDTO.setMessage("Erreur !! L'ancien mot de passe est erroné...");
        }
        return updatePwdUserResponseDTO;
    }

    @Override
    public AppUser turnOffOrOnUser(TurnOffOrOnUserDTO dto) {

        AppUser appUser = appUserRepository.findByUsername(dto.getUsername());

        if (appUser == null) throw new RuntimeException("The username is not exists");

        appUser.setAccountNonLocked(dto.getAccountNonLockedStatus());

        return appUserRepository.save(appUser);
    }


    @Override
    public String initAllPwd() {

        appUserRepository.findAll().forEach(appUser -> {
            appUser.setPassword(passwordEncoder.encode(appUser.getUsername() + "@srmanager"));
            appUserRepository.save(appUser);
        });
        return "Init Password all users is completed at : " + LocalTime.now();
    }


    /*@Override
    public AppUser saveUser(AppUser appUser) {

        //String pwd = appUser.getUsername() + "@srmanager";
        //appUser.setAvatar(appUser.getUsername() + ".png");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }*/


    @Value(value = "${dbServer}")
    String dbServer;

    @Value(value = "${dbName}")
    String dbName;

    @Value(value = "${spring.datasource.password}")
    String dbPassword;

    @Value(value = "${spring.datasource.username}")
    String dbUserName;

    //String sqlPath=SrUtils.sqlPath;
    //String shortOutputFile=SrUtils.BACKUP_PATH;


    @Override
    //@Scheduled(cron = "0 0 22 * * *")
    public String backupDB() {
        String fullOutputFile;

        fullOutputFile = SrUtils.BACKUP_PATH + dbName
                + "-" + LocalDateTime.now().toString().split("T")[0]
                + "-" + LocalDateTime.now().getHour() + "h"
                + "-" + LocalDateTime.now().getMinute() + "m.sql";

        String executeCmd;

        executeCmd = SrUtils.sqlPath + " -h " + dbServer + " -u " + dbUserName + " -p " + dbPassword
                + " --add-drop-database -B " + dbName + " -r " + fullOutputFile;


        // executeCmd= "/Applications/XAMPP/xamppfiles/bin/mysqldump -h localhost -u root -p --add-drop-database -B sr_users -r /Users/mac/dump/sr_users-2022-09-14-12h-12m.sql";

        log.info(executeCmd);

        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);

            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return "Backup created successfully";
            } else {
                return "Could not create the backup";
            }
        } catch (Exception ex) {
            log.info(ex.getMessage());
            ex.printStackTrace();
        }

        return "Could not create the backup";
    }


    @Override
    public AppUser createUser(CreateUserDTO createUserDTO) {

        log.info("*********  ***************");
        log.info("CreateUser 2");
        log.info("FirstName " + createUserDTO.getFirstName());
        log.info("LastName " + createUserDTO.getLastName());

        int len = 10;
        int randNumOrigin = 48, randNumBound = 122;
        String pwd = generateRandomPassword(len, randNumOrigin, randNumBound);
        log.info("New PWD ==> " + pwd);

        //appUser.setPassword(passwordEncoder.encode(appUser.getUsername() + "@srmanager"));
        //appUser.setPassword(passwordEncoder.encode(pwd));
        //appUserRepository.save(appUser);

        AppUser appUser = appUserRepository.findByUsername(createUserDTO.getUsername());
        if (appUser == null) {
            appUser = new AppUser(null,
                    createUserDTO.getFirstName(),
                    createUserDTO.getLastName(),
                    createUserDTO.getFirstName() + ' ' + createUserDTO.getLastName(),
                    createUserDTO.getUsername(),
                    passwordEncoder.encode(pwd),
                    createUserDTO.getAvatar(),
                    null,
                    "",
                    "",
                    new ArrayList<>(),
                    true,
                    createUserDTO.getEmail(),
                    null,
                    true,
                    true,
                    true,
                    true,
                    null,
                    false);
            appUserRepository.save(appUser);
        }

        if (!createUserDTO.getRoles().isEmpty()) {
            AppUser finalAppUser = appUser;
            createUserDTO.getRoles().forEach(roleName -> {
                AppRole appRolex = appRoleRepository.findByRoleName(roleName).orElse(null);
                if (appRolex == null) {
                    log.info("The role [" + roleName + "] is not exists");
                    throw new RuntimeException("The role [" + roleName + "] is not exists");
                }

                if (!finalAppUser.getAppRoles().contains(appRolex)) {
                    log.info("Approle add Id " + appRolex.getId());
                    finalAppUser.getAppRoles().add(appRolex);
                }

               /* List<String>roles=finalAppUser.getAppRoles()
                        .stream()
                        .map(appRole -> appRole.getRoleName())
                        .collect(Collectors.toList());*/

               /* if(roles.indexOf(ConstRoleName.COND_CH)!=-1
                        || roles.indexOf(ConstRoleName.TECH_CH)!=-1){
                    finalAppUser.setWorkingDays("25;26;27;28;29;30");
                    appUserRepository.save(finalAppUser);

                }

                if(roles.indexOf(ConstRoleName.CHEF_ZONE)!=-1){
                    finalAppUser.setWorkingDays("25;26;27;28;29;30;31;1;2");
                    appUserRepository.save(finalAppUser);

                }*/
            });
        }

        log.info("************  ************");

        if (!appUser.getAppRoles().isEmpty()) {
            appUser.setRole(appUser.getAppRoles().get(0).getRoleName());
            appUserRepository.save(appUser);
        }

//        StringBuilder text = new StringBuilder("<html><body>");
//        text = text.append("<h1>Srmanager : </h1>");
//        text = text.append("<h2>Votre Nouveau mot de passe est : </h2>");
//        text = text.append("<h3>" + pwd + "</h3>");
//        text = text.append("</body></html>");


        //StringBuilder text = new StringBuilder("Votre Nouveau mot de passe SRMANAGER est ==>  "+pwd);

        String htmlMsg = "<body> " +
                "<div style='border:2px solid black'>"
                + " <h1>" + appUser.getFullName().toUpperCase() + ",</h1>"
                + " <h2> Votre Nouveau mot de passe SRMANAGER est ==> <strong>" + pwd + "<strong></h2>"
                + "</div> " +
                "</body>";

        MailSendDTO dto = new MailSendDTO(appUser.getEmail(), "Initialisation du mot de passe Srmanager", htmlMsg);
        //mailSendRestClient.sendMail(dto, createUserDTO.getToken());
        return appUser;
    }

    @Override
    public SrResponseMessage updateWorkingDays() {
        List<AppUser> appUsers = appUserRepository.findAll();
        appUsers.forEach(appUser -> {

            List<String> roles = appUser.getAppRoles()
                    .stream()
                    .map(appRole -> appRole.getRoleName())
                    .collect(Collectors.toList());

            log.info("Full name => " + appUser.getFullName());
            log.info("Les roles => ");
            log.info(String.valueOf(roles.size()));
            log.info(roles.toString());


            if (roles.contains(ConstRoleName.COND_CH)
                    || roles.contains(ConstRoleName.TECH_CH)) {
                appUser.setWorkingDays("25;26;27;28;29;30");
                appUserRepository.save(appUser);

            }

            if (roles.contains(ConstRoleName.CHEF_ZONE)) {
                appUser.setWorkingDays("25;26;27;28;29;30;31;1;2");
                appUserRepository.save(appUser);

            }
            log.info("Working Days =>");
            log.info(appUser.getWorkingDays());

        });
        return new SrResponseMessage(true, "Working Days updated for all users");
    }


    @Override
    public String generateRandomPassword(int len, int randNumOrigin, int randNumBound) {
        SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public SrResponseMessage initPwd(String username, String token) {

        try {
            log.info("initPwd username->" + username);

            AppUser appUser = appUserRepository.findByUsername(username);
            if (appUser == null) {
                log.info("The username - " + username + " -is not exists");
                throw new RuntimeException("The username is not exists");
            }

            int len = 10;
            int randNumOrigin = 48, randNumBound = 122;
            String pwd = generateRandomPassword(len, randNumOrigin, randNumBound);
            log.info("New PWD ==> " + pwd);

            //appUser.setPassword(passwordEncoder.encode(appUser.getUsername() + "@srmanager"));
            appUser.setPassword(passwordEncoder.encode(pwd));
            appUserRepository.save(appUser);

//            StringBuilder text = new StringBuilder("<h1>Srmanager : <h1>");
//            text = text.append("<h2>Votre Nouveau mot de passe est : <h2>");
//            text = text.append("<h3>" + pwd + "<h3>");
//

            // StringBuilder text = new StringBuilder("Votre Nouveau mot de passe est ==>  "+pwd);

            String htmlMsg = "<body> " +
                    "<div style='padding: 0.75rem 1.25rem; " +
                    " margin-bottom:1rem; " +
                    " border: 1px solid transparent; " +
                    " border-radius: 0.25rem; " +
                    " color: #155724; " +
                    " background-color:#d4edda; " +
                    " border-color:#c3e6cb'>" +
                    " <h1>"+ appUser.getFullName().toUpperCase() + ",</h1>"+
                    " <h2> Votre login SRMANAGER (https://srmanager.ma/) est : <strong style='color: #721c24'>"+ appUser.getUsername() +"</strong></h2>"+
                    " <h2> Votre nouveau mot de passe SRMANAGER est: <strong  style='color: #721c24'>"+ pwd +"</strong></h2>"+
                    " </div> " +
                    " </body>";


            MailSendDTO dto = new MailSendDTO(appUser.getEmail(), "Initialisation du mot de passe Srmanager", htmlMsg);

            //mailSendRestClient.sendMail(dto, token);

            return new SrResponseMessage(true, pwd);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return new SrResponseMessage(false, "");

    }

}
