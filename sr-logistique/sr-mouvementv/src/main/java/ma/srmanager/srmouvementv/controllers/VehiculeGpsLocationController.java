package ma.srmanager.srmouvementv.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.AssociateChauffeurAndPriceDTO;
import ma.srmanager.srmouvementv.model.VehiculeGpsLocation;
import ma.srmanager.srmouvementv.services.VehiculeGpsLocationService;
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
            // Handle potential string inputs by converting them to numbers
            // Long chauffeurId = Long.parseLong(requestParams.get("chauffeurId").toString());
            // Double costPerKm = Double.parseDouble(requestParams.get("cost_per_km").toString());

            vehiculeGpsLocationService.associateChauffeurAndPrice(
                    associateChauffeurAndPriceDTO.getVehiculeId(),
                    associateChauffeurAndPriceDTO.getChauffeurId(),
                    associateChauffeurAndPriceDTO.getCostPerKm());


        } catch (NumberFormatException e) {
            log.info(e.getMessage());
        }
        return getAllVehiculeGps();
    }


    @GetMapping("/getAllVehiculeGps")
    public List<VehiculeGpsLocation> getAllVehiculeGps() {
        return vehiculeGpsLocationService.getAllVehiculeGps();

    }

    @DeleteMapping("/deleteVehicule/{id}")
    public List<VehiculeGpsLocation> deleteVehicule(@PathVariable Long id) {
        try {
            vehiculeGpsLocationService.deleteVehicule(id);

        } catch (RuntimeException e) {
            log.info(e.getMessage());
        }
        return getAllVehiculeGps();
    }

}
