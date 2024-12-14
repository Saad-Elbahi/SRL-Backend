package org.example.enginservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enginservice.dto.EnginGpsDTO;
import org.example.enginservice.entities.EnginGpsLocation;
import org.example.enginservice.service.EnginGpsLocationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/EnginGpsapi")
@CrossOrigin("*")
@Slf4j
public class EnginGpsLocationController {
    private final EnginGpsLocationServiceImpl enginGpsLocationService;

    @GetMapping("/saveLocations")
    public void saveEnginGpsLocationFromApi() {
        try {
            enginGpsLocationService.saveEnginGpsLocationFromApi();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }
    @PostMapping("/createEngin")
    public ResponseEntity<EnginGpsLocation> createEnginLocation(@Valid @RequestBody EnginGpsDTO enginGpsDTO) {
        EnginGpsLocation createdLocation = enginGpsLocationService.createEnginLocation(enginGpsDTO);
        return ResponseEntity.ok(createdLocation);
    }
    @PostMapping("/updateEnginGps")
    public List<EnginGpsLocation> associateChauffeurAndPrice(@RequestBody EnginGpsDTO dto) {
        try {
            enginGpsLocationService.updateEnginGpsLocation(dto);

        } catch (NumberFormatException e) {
            log.info(e.getMessage());
        }
        return getAllEnginGps();
    }
    @GetMapping("/getAllEnginGps")
    public List<EnginGpsLocation> getAllEnginGps() {
        return enginGpsLocationService.getAllVehiculeGps();

    }


//    @GetMapping("/fetchRoutesEngin")
//    public void fetchAndSaveAllObjectRoutesEngin(
//            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        enginGpsLocationService.fetchAndSaveAllObjectRoutes(startDate, endDate);
//    }

}
