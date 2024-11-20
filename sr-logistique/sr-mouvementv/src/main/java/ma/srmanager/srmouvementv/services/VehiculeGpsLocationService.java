package ma.srmanager.srmouvementv.services;

import ma.srmanager.srmouvementv.dto.AssociateChauffeurAndPriceDTO;
import ma.srmanager.srmouvementv.entities.VehiculeGpsLocation;

import java.util.List;

public interface VehiculeGpsLocationService {

     List<VehiculeGpsLocation> saveVehiculeFromApi() throws Exception ;


     VehiculeGpsLocation associateChauffeurAndPrice(AssociateChauffeurAndPriceDTO associateChauffeurAndPriceDTO);

     VehiculeGpsLocation getVehiculeById(Long id) ;

     List<VehiculeGpsLocation> getAllVehiculeGps();
     VehiculeGpsLocation updateVehiculeGpsLocation(Long vehiculeId, VehiculeGpsLocation updatedVehiculeGpsLocation);
     void deleteVehicule(Long vehiculeId);

}


