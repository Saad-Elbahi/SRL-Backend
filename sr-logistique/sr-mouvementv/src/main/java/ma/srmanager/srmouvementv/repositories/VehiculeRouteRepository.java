package ma.srmanager.srmouvementv.repositories;

import ma.srmanager.srmouvementv.model.VehiculeRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface VehiculeRouteRepository extends JpaRepository<VehiculeRoute,Long> {

    @Query("SELECT v.vehiculeGpsLocation.vehicleId, v.vehiculeGpsLocation.name, SUM(v.routeLength),SUM(v.costPerTrip)" +
            "FROM VehiculeRoute v " +
            "GROUP BY v.vehiculeGpsLocation.vehicleId, v.vehiculeGpsLocation.name")
    List<Object[]> findTotalRouteLengthByVehicule();

    @Query("SELECT YEAR(vr.date) as year, MONTH(vr.date) as month, SUM(vr.costPerTrip) as totalCost " +
            "FROM VehiculeRoute vr " +
            "GROUP BY YEAR(vr.date), MONTH(vr.date)")
    List<Object[]> getTotalCostPerTripByMonth();
//    @Query("SELECT vgl.affaire.id AS affaireId, vgl.affaire.name AS affaireName, SUM(vr.costPerTrip) AS totalCost " +
//            "FROM VehiculeRoute vr " +
//            "JOIN vr.vehiculeGpsLocation vgl " +
//            "GROUP BY vgl.affaire.id, vgl.affaire.name")
//    List<Object[]> findCostPerAffaireByVehicleLocation();

    Optional<VehiculeRoute> findByVehiculeGpsLocationVehicleIdAndDate(Long vehicleId, LocalDate date);

    List<VehiculeRoute> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<VehiculeRoute> findByDateIn(List<LocalDate> dates);



}
