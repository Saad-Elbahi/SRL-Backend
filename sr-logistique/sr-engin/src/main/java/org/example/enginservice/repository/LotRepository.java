package org.example.enginservice.repository;

import org.example.enginservice.entities.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot,Long> {
}
