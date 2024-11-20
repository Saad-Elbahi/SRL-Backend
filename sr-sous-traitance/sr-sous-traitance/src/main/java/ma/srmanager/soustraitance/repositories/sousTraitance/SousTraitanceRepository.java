package ma.srmanager.soustraitance.repositories.sousTraitance;

import ma.srmanager.coreapi.enums.soustraitance.SousTraitanceStatus;
import ma.srmanager.soustraitance.entities.soustraitance.SousTraitance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SousTraitanceRepository extends JpaRepository<SousTraitance, Long> {

    @Query("select str from SousTraitance str " +
            " where str.marcheId=:marcheId")
    List<SousTraitance> findByMarcheId(Long marcheId);

    @Query("select str from SousTraitance str " +
            " where str.annee=:annee" +
            " order by str.annee desc ")
    List<SousTraitance> findAllByAnnee(int annee);

    List<SousTraitance> findBySubContractorId(Long id);

    List<SousTraitance> findAllBySousTraitanceStatus(SousTraitanceStatus status);

    List<SousTraitance> findByMarcheIdAndSubContractorId(Long marcheId, Long subContractor_id);


}
