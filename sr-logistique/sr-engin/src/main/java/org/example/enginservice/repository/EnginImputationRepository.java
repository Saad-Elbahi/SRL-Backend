package org.example.enginservice.repository;

import org.example.enginservice.entities.EnginImputation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnginImputationRepository  extends JpaRepository<EnginImputation, Long> {
    List<EnginImputation> findByEnginRouteId(Long enginRouteId);

}
