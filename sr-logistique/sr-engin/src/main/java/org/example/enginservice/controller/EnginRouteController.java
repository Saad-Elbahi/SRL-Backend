package org.example.enginservice.controller;

import lombok.AllArgsConstructor;
import org.example.enginservice.dto.EnginRouteDTO;
import org.example.enginservice.entities.EnginRoute;
import org.example.enginservice.service.EnginRouteService;
import org.example.enginservice.service.EnginRouteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/EnginRteapi")
@AllArgsConstructor
public class EnginRouteController {
    private final EnginRouteService enginRouteService;


    //    @GetMapping("/getAllEnginRoutes")
//    public List<EnginRoute> getAllEnginMouvement() {
//        return enginRouteServiceImpl.();
//    }
    @GetMapping("/getAllEnginRoutes")
    public ResponseEntity<List<EnginRoute>> getEnginRoutes() {
        List<EnginRoute> routes = enginRouteService.getAllEnginRoutes();
        return ResponseEntity.ok(routes);
    }

    @PostMapping("/fetch-and-save-yesterday")
    public ResponseEntity<String> fetchAndSaveAllObjectRoutesForYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        try {
            enginRouteService.fetchAndSaveAllObjectRoutes(yesterday, yesterday);
            return ResponseEntity.ok("Routes fetched and saved successfully for yesterday");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleFetchAndSaveYesterdayRoutes() {
        fetchAndSaveAllObjectRoutesForYesterday();
        System.out.println("Routes fetched and saved successfully for yesterday (scheduled task)");
    }

    @PutMapping("/updateRendement")
    public ResponseEntity<List<EnginRoute>> updateMouvement(@RequestBody EnginRouteDTO dto) {
        enginRouteService.updateRendement(dto);
        List<EnginRoute> allRoutes = enginRouteService.getAllEnginRoutes();
        return ResponseEntity.ok(allRoutes);
    }

}
