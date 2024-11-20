package ma.srmanager.srmouvementv.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.AssociateChauffeurAndPriceDTO;
import ma.srmanager.srmouvementv.entities.VehiculeGpsLocation;
import ma.srmanager.srmouvementv.services.VehiculeGpsLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vhgpsapi")
@Slf4j
public class VehiculeGpsLocationController {

    private final VehiculeGpsLocationService vehiculeGpsLocationService;

    @GetMapping("/saveVehiculeFromApi")
    public List<VehiculeGpsLocation> saveVehiculeFromApi() {
        log.info("saveVehiculeFromApi 1");
        try {
            return vehiculeGpsLocationService.saveVehiculeFromApi();
        } catch (Exception e) {
            log.info(e.getMessage());
            // Handle exception as needed
            return getAllVehiculeGps();
        }
    }

    @PostMapping("/associateChauffeurAndPrice")
    public List<VehiculeGpsLocation> associateChauffeurAndPrice(@RequestBody AssociateChauffeurAndPriceDTO associateChauffeurAndPriceDTO) {
        try {
            // Call the service method using the DTO
            vehiculeGpsLocationService.associateChauffeurAndPrice(associateChauffeurAndPriceDTO);

        } catch (NumberFormatException e) {
            log.info(e.getMessage());
        }

        // Return the list of all VehiculeGpsLocations
        return getAllVehiculeGps();
    }


    @GetMapping("/getAllVehiculeGps")
    public List<VehiculeGpsLocation> getAllVehiculeGps() {
        return vehiculeGpsLocationService.getAllVehiculeGps();

    }
    @PutMapping("/updateVehicule/{vehiculeId}")
    public ResponseEntity<VehiculeGpsLocation> updateVehiculeGpsLocation(
            @PathVariable Long vehiculeId,
            @RequestBody VehiculeGpsLocation updatedVehiculeGpsLocation) {

        // Call the service to update the VehiculeGpsLocation
        VehiculeGpsLocation updatedVehicle = vehiculeGpsLocationService.updateVehiculeGpsLocation(vehiculeId, updatedVehiculeGpsLocation);

        // Return the updated VehiculeGpsLocation as a response
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/deleteVehicule/{vehiculeId}")
    public List<VehiculeGpsLocation> deleteVehicule(@PathVariable Long vehiculeId) {
        try {
            vehiculeGpsLocationService.deleteVehicule(vehiculeId);
            log.info(vehiculeId.toString());
        } catch (RuntimeException e) {
            log.info(e.getMessage());
        }
        return getAllVehiculeGps();
    }

}
