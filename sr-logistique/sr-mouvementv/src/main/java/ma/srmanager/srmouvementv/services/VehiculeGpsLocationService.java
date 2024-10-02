package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.model.VehiculeGpsLocation;
import java.util.List;

public interface VehiculeGpsLocationService {

     List<VehiculeGpsLocation> saveVehiculeFromApi() throws Exception ;

     VehiculeGpsLocation associateChauffeurAndPrice(Long vehiculeId, Long chauffeurId, Double costPerKm) ;

     VehiculeGpsLocation getVehiculeById(Long id) ;

     List<VehiculeGpsLocation> getAllVehiculeGps();

     void deleteVehicule(Long id);

}


