package ma.srmanager.sraffaires.repositories.bordereau;

import ma.srmanager.coreapi.enums.marche.ProjetStatus;
import ma.srmanager.sraffaires.entities.imputation.Marche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MarcheRepository extends JpaRepository<Marche, Long> {

    Marche findByNumero(String numero);

    Optional<Marche> findByCode(String code);

    Marche findByNumeroEquals(String numero);


    Marche findFirstById(Long id);

    @Query("select m from Marche m " +
            " where m.projetStatus=:projetStatus" +
            " order by m.code")
    List<Marche> byProjetStatus(ProjetStatus projetStatus);

    @Query("select m from Marche m " +
            " where m.projetStatus=:projetStatus" +
            " and m.chefZoneUsername=:username" +
            " order by m.code")
    List<Marche> byProjetStatusAndChefZoneUsername(ProjetStatus projetStatus, String username);

    @Query("select m from Marche m " +
            " where m.projetStatus=:projetStatus" +
            " and m.technicienUsername=:username" +
            " order by m.code")
    List<Marche> byProjetStatusAndTechnicienUsername(ProjetStatus projetStatus, String username);

    @Query("select m from Marche m " +
            " where m.projetStatus=:projetStatus" +
            " and m.conducteurUsername=:username" +
            " order by m.code")
    List<Marche> byProjetStatusAndConducteurUsername(ProjetStatus projetStatus, String username);

    @Query("select m from Marche m " +
            " where m.projetStatus=:projetStatus" +
            " and m.pointeurUsername=:username" +
            " order by m.code")
    List<Marche> byProjetStatusAndPointeurUsername(ProjetStatus projetStatus, String username);

    @Query("select m from Marche m " +
            " where m.projetStatus=:projetStatus" +
            " and m.comptableUsername=:username" +
            " order by m.code")
    List<Marche> byProjetStatusAndComptableUsername(ProjetStatus projetStatus, String username);

    @Query("select m from Marche m " +
            " where m.projetStatus=:projetStatus" +
            " and m.magazinierUsername=:username" +
            " order by m.code")
    List<Marche> byProjetStatusAndMagazinierUsername(ProjetStatus projetStatus, String username);

    @Query("select m from Marche m " +
            " where m.projetStatus=:projetStatus" +
            " and m.chefChantierUsername=:username" +
            " order by m.code")
    List<Marche> byProjetStatusAndChefChantierUsername(ProjetStatus projetStatus, String username);


    @Query("select m from Marche m " +
            " where m.chefZoneUsername=:username" +
            " order by m.code")
    List<Marche> byChefZoneUsername(String username);

    @Query("select m from Marche m " +
            " where m.technicienUsername=:username" +
            " order by m.code")
    List<Marche> byTechnicienUsername( String username);

    @Query("select m from Marche m " +
            " where m.conducteurUsername=:username" +
            " order by m.code")
    List<Marche> byConducteurUsername( String username);

    @Query("select m from Marche m " +
            " where m.pointeurUsername=:username" +
            " order by m.code")
    List<Marche> byPointeurUsername( String username);

    @Query("select m from Marche m " +
            " where m.comptableUsername=:username" +
            " order by m.code")
    List<Marche> byComptableUsername( String username);

    @Query("select m from Marche m " +
            " where m.magazinierUsername=:username" +
            " order by m.code")
    List<Marche> byMagazinierUsername( String username);

    @Query("select m from Marche m " +
            " where m.chefChantierUsername=:username" +
            " order by m.code")
    List<Marche> byChefChantierUsername(String username);

    //List<Marche> findByAllowPointage(boolean allowPointage);

}
