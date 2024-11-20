package ma.srmanager.soustraitance.repositories.subcontractor;

import ma.srmanager.soustraitance.entities.subcontractor.SubContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubContractorRepository extends JpaRepository<SubContractor, Long> {


    @Query("select s from SubContractor s order by s.fullName")
    List<SubContractor> getAllOrderByFullName();

}
