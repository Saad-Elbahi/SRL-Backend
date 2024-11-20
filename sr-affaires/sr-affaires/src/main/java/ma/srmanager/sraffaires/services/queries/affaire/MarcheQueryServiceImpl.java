package ma.srmanager.sraffaires.services.queries.affaire;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ma.srmanager.coreapi.enums.marche.ProjetStatus;

import ma.srmanager.sraffaires.entities.imputation.Marche;
import ma.srmanager.sraffaires.repositories.bordereau.MarcheRepository;
import ma.srmanager.srjwt.coreapi.jwt.ConstRoleName;
import ma.srmanager.srjwt.coreapi.jwt.JWTUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class MarcheQueryServiceImpl implements MarcheQueryService {
    private MarcheRepository marcheRepository;
   // private CompteExploitationQueryService compteExploitationQueryService;

    @Override
    public List<Marche> byStatus(ProjetStatus projetStatus,String token) {
        List<Marche> marches;
        token = token.substring(JWTUtil.JWT_HEADER_PREFIX.length());
        Algorithm algo1 = Algorithm.HMAC256(JWTUtil.JWT_SECRET);
        JWTVerifier jwtVerifier = JWT.require(algo1).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim(JWTUtil.JWT_CLAIMS_ROLES).asArray(String.class);


      /*  Arrays.stream(roles).forEach(role->{
            log.info(role);
        });
*/
       /* else if(Arrays.stream(roles)
                .filter(role->role.equals(ConstRoleName.CHEF_ZONE))
                .findFirst()
                .orElse(null)!=null){

            marches= marcheRepository.byProjetStatusAndChefZoneUsername(projetStatus, username);

        }
        */

        if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.ADMIN)){
            marches=marcheRepository.byProjetStatus(projetStatus);

        }else if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.PDG)){
            marches= marcheRepository.byProjetStatus(projetStatus);

        }else  if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.DGA)){
            marches= marcheRepository.byProjetStatus(projetStatus);

        }else  if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.DAF)){
            marches= marcheRepository.byProjetStatus(projetStatus);

        }else if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.CHEF_ZONE)){
            marches= marcheRepository.byProjetStatusAndChefZoneUsername(projetStatus, username);

        } else if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.TECH_CH)){

            marches= marcheRepository.byProjetStatusAndTechnicienUsername(projetStatus, username);

        }else if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.COND_CH)){

            marches= marcheRepository.byProjetStatusAndConducteurUsername(projetStatus, username);

        }else if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.COMPT_CH)){

            marches= marcheRepository.byProjetStatusAndComptableUsername(projetStatus, username);

        }else if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.POINTEUR)){

            marches= marcheRepository.byProjetStatusAndPointeurUsername(projetStatus, username);

        }else if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.MAGASINIER_CH)){
            marches= marcheRepository.byProjetStatusAndMagazinierUsername(projetStatus, username);

        }else if(Arrays.stream(roles).collect(Collectors.toList()).contains(ConstRoleName.CHEF_CH)){
            marches= marcheRepository.byProjetStatusAndChefChantierUsername(projetStatus, username);

        }else{
            marches= marcheRepository.byProjetStatus(projetStatus);
        }

        //marches.forEach(marche ->  log.info(marche.getCode()));

//        marches.forEach(
//                marche -> marche.setCompteExploitation(compteExploitationQueryService.cptExpByMarche(marche.getId()))
//        );
        return marches;
    }

    @Override
    public Marche marcheById(Long id) {
//        log.info("*********************************************************************");
        log.info("**** marcheById  *********");
        log.info("ID = "+id);
//        log.info("*********************************************************************");

        return marcheRepository.findById(id).orElse(null);
//        return repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Marche not fount"));
    }

    @Override
    public Optional<Marche> marcheByCode(String code) {
        return marcheRepository.findByCode(code);
    }




}
