package org.example.enginservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.example.enginservice.dto.EnginRouteDTO;
import org.example.enginservice.entities.EnginGpsLocation;
import org.example.enginservice.entities.EnginRoute;
import org.example.enginservice.repository.EnginGpsLocationRepository;
import org.example.enginservice.repository.EnginRouteRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class EnginRouteServiceImpl implements EnginRouteService {

    private final EnginGpsLocationRepository enginGpsLocationRepository;
    private final EnginRouteRepository enginRouteRepository;
    private final RestTemplate restTemplate;
    private final String apiKey = "88918E46B26489F0ECDC7966541FE2A9";
    private final String baseUrl = "https://rouandigps.com/api/api.php";

    private final ObjectMapper objectMapper;
    private static final Logger logger = Logger.getLogger(EnginGpsLocationServiceImpl.class.getName());
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /*private final String apiKey = "88918E46B26489F0ECDC7966541FE2A9";
    private final String baseUrl = "https://rouandigps.com/api/api.php";

    private final ObjectMapper objectMapper;
    private static final Logger logger = Logger.getLogger(EnginGpsLocationServiceImpl.class.getName());
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");




    @Transactional
    public List<EnginRoute> getAllEnginRoutes() {
        List<EnginRoute> mouvements = enginRouteRepository.findAll();
        try {
            String jsonResponse = objectMapper.writeValueAsString(mouvements);
            log.info("JSON Response: " + jsonResponse);
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON", e);
        }
        return mouvements;
    }
    @Transactional
    public void fetchAndSaveAllObjectRoutes(LocalDate startDate, LocalDate endDate) {
        List<EnginGpsLocation> allObjects = enginGpsLocationRepository.findAll();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            for (EnginGpsLocation object : allObjects) {
                fetchAndSaveRouteForObject(object, date);
            }
        }
    }


    }*/
    @Transactional
    @Override
    public void fetchAndSaveAllObjectRoutes(LocalDate startDate, LocalDate endDate) {
        List<EnginGpsLocation> allEngin = enginGpsLocationRepository.findAll();
       List<EnginGpsLocation> enginsWithoutGps = enginGpsLocationRepository.findAllWithoutGps();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            for (EnginGpsLocation engin : allEngin) {
                log.info("Fetch and save Route VH==>" + engin.getName());
                fetchAndSaveRouteForObject(engin, date);
            }
            for (EnginGpsLocation engin : enginsWithoutGps) {
               processNonGpsEngin(engin, date);
           }
        }
    }

    public void fetchAndSaveRouteForObject(EnginGpsLocation object, LocalDate date) {
        LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.of(23, 59, 59));

        String imei = object.getImei();
        String apiUrl = String.format("%s?api=user&key=%s&cmd=OBJECT_GET_ROUTE,%s,%s,%s,1", baseUrl, apiKey, imei, startDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), endDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        boolean success = false;
        int attempts = 0;
        int maxAttempts = 3;
        long waitTime = 1000;

        while (!success && attempts < maxAttempts) {
            try {
                String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
                attempts++;

                if (jsonResponse != null && !jsonResponse.isEmpty()) {
                    if (jsonResponse.startsWith("ERROR")) {
                        logger.warning("API Error: " + jsonResponse);
                        if (jsonResponse.contains("API call limit exceeded")) {
                            logger.info("Waiting for " + waitTime + " ms before retrying...");
                            TimeUnit.MILLISECONDS.sleep(waitTime);
                            waitTime *= 2; // Exponential backoff
                            continue;
                        } else {
                            break;
                        }
                    }

                    JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                    if (jsonNode.has("route_length")) {
                        double routeLength = jsonNode.get("route_length").asDouble();


                        String drivesDurationStr = jsonNode.get("drives_duration").asText(); //  "01:15:30"
                        String engineIdleStr = jsonNode.get("engine_idle").asText();         //  "00:20:15"

                        Duration drivesDuration = parseIsoDuration(drivesDurationStr);
                        Duration engineIdle = parseIsoDuration(engineIdleStr);

                        // Format durations to HH:mm:ss
                        String formattedDrivesDuration = formatDurationToString(drivesDuration);
                        String formattedEngineIdle = formatDurationToString(engineIdle);

                        // Calculate rendement
                        Duration totalRendement = drivesDuration.plus(engineIdle);
                        String formattedRendement = formatDurationToString(totalRendement);

                        EnginRoute enginRoute = new EnginRoute();
                        enginRoute.setDate(date);
                        enginRoute.setRouteLength(routeLength);

                        // Store durations as formatted strings (HH:mm:ss)
                        enginRoute.setDrivesDuration(formattedDrivesDuration);
                        enginRoute.setEngineIdle(formattedEngineIdle);
                        enginRoute.setRendement(formattedRendement);

                        // Assume enginRoute.getRendement() returns a String like "12:30:45"
                        String rendementTime = enginRoute.getRendement();
                        Double totalHours = null;
                        try {
                            // Split the time string into hours, minutes, and seconds
                            String[] timeParts = rendementTime.split(":");
                            int hours = Integer.parseInt(timeParts[0]);
                            int minutes = Integer.parseInt(timeParts[1]);
                            int seconds = Integer.parseInt(timeParts[2]);

                            // Calculate total hours as a double
                            totalHours = hours + (minutes / 60.0) + (seconds / 3600.0);

                            // Set the calculated hours into nbrH
                            enginRoute.setNbrH(totalHours); // Change to double if nbrH accepts it

                            System.out.println("Rendement Time: " + rendementTime);
                            System.out.println("Total Hours (Exact): " + totalHours);
                        } catch (Exception e) {
                            System.err.println("Error parsing rendement time: " + rendementTime);
                        }
                        // Calculate montant (nbrH * coutH)
                        Double coutH = object.getCoutH();
                        if (coutH != null && totalHours != null) {
                            Double montant = totalHours * coutH;
                            enginRoute.setMontant(montant);
                            logger.info("Montant calculated successfully: " + montant);
                        } else {
                            logger.warning("Failed to calculate montant due to null values for nbrH or coutH.");
                        }

                        enginRoute.setEnginGpsLocation(object);

                        enginRouteRepository.save(enginRoute);

                        logger.info("Drives Duration: " + formattedDrivesDuration);
                        logger.info("Engine Idle: " + formattedEngineIdle);
                        logger.info("Rendement: " + formattedRendement);
                        logger.info("Totale hours " + totalHours);


                        success = true;
                    } else {
                        logger.warning("Required fields are missing in JSON response");
                        break;
                    }
                } else {
                    logger.warning("JSON data is empty");
                    break;
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "IOException occurred", e);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "InterruptedException occurred", e);
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Unexpected error occurred", e);
            }
        }

        if (!success) {
            logger.warning("Failed to process route for IMEI: " + imei + " on date: " + date);
        }
    }

    // Convert Duration to String in "H:mm:ss" format
    private String formatDurationToString(Duration duration) {
        long millis = duration.toMillis();
        return DurationFormatUtils.formatDuration(millis, "H:mm:ss", true);
    }

    // Duration parsing method
    public static Duration parseIsoDuration(String duration) {
        int totalSeconds = 0;
        Pattern pattern = Pattern.compile("(\\d+)\\s*h|(\\d+)\\s*min|(\\d+)\\s*s");
        Matcher matcher = pattern.matcher(duration);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                totalSeconds += Integer.parseInt(matcher.group(1)) * 3600; // Hours to seconds
            }
            if (matcher.group(2) != null) {
                totalSeconds += Integer.parseInt(matcher.group(2)) * 60; // Minutes to seconds
            }
            if (matcher.group(3) != null) {
                totalSeconds += Integer.parseInt(matcher.group(3)); // Seconds
            }
        }
        return Duration.ofSeconds(totalSeconds);
    }

    @Override
    public List<EnginRoute> getAllEnginRoutes() {
        List<EnginRoute> routesEngin = enginRouteRepository.findAll();
        for (EnginRoute route : routesEngin) {
            route.setImputationStatus(route.getImputations() != null && !route.getImputations().isEmpty());
        }
        return routesEngin;
    }

    @Override
    public EnginRoute updateRendement(EnginRouteDTO dto) {
        EnginRoute enginRoute = enginRouteRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("EnginRoute with Id" + dto.getId() + "not found"));
        enginRoute.setRendement(dto.getRendement());
        String rendementTime = enginRoute.getRendement();
        Double totalHours = null;
        try {
            String[] timeParts = rendementTime.split(":");
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            int seconds = Integer.parseInt(timeParts[2]);

            totalHours = hours + (minutes / 60.0) + (seconds / 3600.0);

            enginRoute.setNbrH(totalHours);

            System.out.println("Rendement Time: " + rendementTime);
            System.out.println("Total Hours (Exact): " + totalHours);
        } catch (Exception e) {
            System.err.println("Error parsing rendement time: " + rendementTime);
        }
        Double coutH = enginRoute.getEnginGpsLocation().getCoutH();
        if (coutH != null && totalHours != null) {
            Double montant = totalHours * coutH;
            enginRoute.setMontant(montant);
            logger.info("Montant calculated successfully: " + montant);
        } else {
            logger.warning("Failed to calculate montant due to null values for nbrH or coutH.");
        }
        return enginRouteRepository.save(enginRoute);
    }

    private void processNonGpsEngin(EnginGpsLocation engin, LocalDate date) {
        EnginRoute enginRoute = new EnginRoute();
        enginRoute.setDate(date);
        enginRoute.setRouteLength(0.0);
        enginRoute.setDrivesDuration("00:00:00");
        enginRoute.setEngineIdle("00:00:00");
        enginRoute.setRendement("00:00:00");
        enginRoute.setNbrH(0.0);
        enginRoute.setMontant(0.0);
        enginRoute.setEnginGpsLocation(engin);

        enginRouteRepository.save(enginRoute);

        logger.info("Processed non-GPS engin: " + engin.getName() + " on date: " + date);
    }


}
