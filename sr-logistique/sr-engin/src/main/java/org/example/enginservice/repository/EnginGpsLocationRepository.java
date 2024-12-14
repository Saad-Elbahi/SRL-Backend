package org.example.enginservice.repository;

import org.example.enginservice.entities.EnginGpsLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnginGpsLocationRepository extends JpaRepository<EnginGpsLocation, Long> {
//    List<EnginGpsLocation> findTop2ByImeiOrderByDtServerDesc(String imei);

    @Query("SELECT e FROM EnginGpsLocation e WHERE e.imei IS NULL OR e.imei = ''")
    List<EnginGpsLocation> findAllWithoutGps();
}