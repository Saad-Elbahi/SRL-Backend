package ma.srmanager.srparams.services.commandes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.params.dtos.CreateSocieteDTO;
import ma.srmanager.srparams.entities.Banque;
import ma.srmanager.srparams.entities.Param;
import ma.srmanager.srparams.entities.Societe;
import ma.srmanager.srparams.repositories.BanqueRepository;
import ma.srmanager.srparams.repositories.ParamRepository;
import ma.srmanager.srparams.repositories.SocieteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class SocieteServiceImpl implements SocieteService {
    private final BanqueRepository banqueRepository;
    private SocieteRepository societeRepository;
    private ParamRepository paramRepository;

    @Override
    public Societe createSociete(CreateSocieteDTO dto) {
        Societe societe = new Societe();
        societe.setRaisonSociele(dto.getRaisonSociele());
        societe.setSigle(dto.getSigle());
        societe.setPathLogo(dto.getPathLogo());
        societe.setEmail(dto.getEmail());
        societe.setAdresse1(dto.getAdresse1());
        societe.setAdresse2(dto.getAdresse2());
        societe.setCodePostal(dto.getCodePostal());
        Param ville=paramRepository.findById(Objects.requireNonNull(dto.getVilleId())).orElse(null);
        if(ville==null){
            log.info("Ville not exists");
           throw new NullPointerException("Ville not exists");
        }
        societe.setVille(ville);
        societe.setIce(dto.getIce());
        societe.setIndiceFiscal(dto.getIndiceFiscal());
        societe.setPatente(dto.getPatente());
        societe.setRc(dto.getRc());
        societe.setCnss(dto.getCnss());
        societe.setDateCreation(LocalDate.now());
        societe.setCiviliteManager(dto.getCiviliteManager());
        societe.setPrenomManager(dto.getPrenomManager());
        societe.setNomManager(dto.getNomManager());
        societe.setCinManager(dto.getCinManager());
        societe.setGsmManager(dto.getGsmManager());
        societe.setCodeManager(dto.getCodeManager());
        societe.setAvatarManager(dto.getAvatarManager());
        societe.setEmailManager(dto.getEmailManager());
        societeRepository.save(societe);
        dto.getBanques().forEach(banqueDto -> {
            Banque banque = new Banque();
            banque.setIntitule(banqueDto.getIntitule());
            banque.setSigle(banqueDto.getSigle());
            banque.setRib(banqueDto.getRib());
            banque.setAgence(banqueDto.getAgence());
            banque.setSociete(societe);
            banqueRepository.save(banque);
        });
        return societe;
    }
}
