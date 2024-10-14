package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FournisseurRepository extends JpaRepository<Fournisseur,Long > {

}
