package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.entities.VehiculeGpsLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VehiculeGpsLocationRepository extends JpaRepository<VehiculeGpsLocation, Long> {
    //
//    @Query("SELECT v FROM VehiculeGpsLocation v WHERE v.dtTracker >= :yesterday AND v.id = :vehicleId ORDER BY v.dtTracker ASC")
//    List<VehiculeGpsLocation> findEarliestLocationSince(@Param("yesterday") LocalDateTime yesterday, @Param("vehicleId") Long id);
//
//    @Query("SELECT v FROM VehiculeGpsLocation v WHERE v.dtTracker < :today AND v.id = :vehicleId ORDER BY v.dtTracker DESC")
//    List<VehiculeGpsLocation> findLocationsBefore(@Param("today") LocalDateTime today, @Param("vehicleId") Long id);
// Method to find the most recent VehiculeGpsLocation by vehicle ID
//@Query("SELECT vgl FROM VehiculeGpsLocation vgl WHERE vgl.id = :id ORDER BY vgl.dtServer DESC")
    List<VehiculeGpsLocation> findMostRecentByVehicleId(Long id);

    List<VehiculeGpsLocation> findByImei(String imei);

    List<VehiculeGpsLocation> findByNameAndGroupName(String name, String groupName);


}
