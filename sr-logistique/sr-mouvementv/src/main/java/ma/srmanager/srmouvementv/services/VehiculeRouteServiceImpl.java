package ma.srmanager.srmouvementv.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.PerformanceOverTimeRequestDTO;
import ma.srmanager.srmouvementv.dto.UpdateFillingPercentageDTO;
import ma.srmanager.srmouvementv.dto.UpdateMouvementDTO;
import ma.srmanager.srmouvementv.entities.TripImputation;
import ma.srmanager.srmouvementv.entities.VehiculeGpsLocation;
import ma.srmanager.srmouvementv.entities.VehiculeRoute;
import ma.srmanager.srmouvementv.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VehiculeRouteServiceImpl implements VehiculeRouteService {

    @Autowired
    private VehiculeRouteRepository vehiculeRouteRepository;
    @Autowired
    private VehiculeGpsLocationRepository vehiculeGpsLocationRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private FromMouvementRepository fromMouvementRepository;
    @Autowired
    private TripImputationRepository tripImputationRepository;

    @Autowired
    private LotRepository lotRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AffaireService affaireService;
    @Autowired
    private SubContractorService subContractorService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TripImputationService tripImputationService;

    private static final Logger logger = Logger.getLogger(VehiculeGpsLocationServiceImpl.class.getName());
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String fileUploadDir = System.getProperty("user.home") + "/Apps/sr-manager/RESSOURCES/data/assets/images/";
    private static final String gpsApiKey = "88918E46B26489F0ECDC7966541FE2A9";
    private static final String gpsBaseUrl = "https://rouandigps.com/api/api.php";




    @Transactional
    @Override
    public VehiculeRoute getVehiculeRouteById(Long id) {

        return vehiculeRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement not found with id: " + id));
    }

    @Transactional
    @Override
    public void fetchAndSaveAllObjectRoutes(LocalDate startDate, LocalDate endDate) {
        List<VehiculeGpsLocation> allVehicles = vehiculeGpsLocationRepository.findAll();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            for (VehiculeGpsLocation vehicle : allVehicles) {
                log.info("Fetch and save Route VH==>" + vehicle.getName());
                fetchAndSaveRouteForVehicle(vehicle, date);
            }
        }
    }

    @Override
    public void fetchAndSaveRouteForVehicle(VehiculeGpsLocation vehicle, LocalDate date) {
        LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.of(23, 59, 59));

        String imei = vehicle.getImei();
        String apiUrl = String.format("%s?api=user&key=%s&cmd=OBJECT_GET_ROUTE,%s,%s,%s,1",
                gpsBaseUrl, gpsApiKey, imei, startDateTime.format(formatter), endDateTime.format(formatter));

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
                            waitTime *= 2;
                            continue;
                        } else {
                            break;
                        }
                    }

                    JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                    if (jsonNode.has("route_length")) {
                        double routeLength = jsonNode.get("route_length").asDouble();

                        VehiculeRoute vehiculeRoute = new VehiculeRoute();
                        vehiculeRoute.setDate(date);
                        vehiculeRoute.setRouteLength(routeLength);
                        vehiculeRoute.setVehiculeGpsLocation(vehicle);


                        vehiculeRouteRepository.save(vehiculeRoute);
                        logger.info("VehiculeRoute saved: " + vehiculeRoute);

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

    @Override
    public VehiculeRoute updateMouvement(UpdateMouvementDTO dto) {
        VehiculeRoute vehiculeRoute = vehiculeRouteRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("VehiculeRoute with Id " + dto.getRouteLength() + "not found "));
        vehiculeRoute.setRouteLength(dto.getRouteLength());
        return vehiculeRouteRepository.save(vehiculeRoute);
    }

    /*    @Transactional
        @Override
        public VehiculeRoute associateFromMouvementsAndTo(Long vehiculeRouteId, List<FromMouvement> fromMouvements) {

            // Retrieve the VehiculeRoute for the given vehiculeRouteId
            VehiculeRoute vehiculeRoute = vehiculeRouteRepository.findById(vehiculeRouteId)
                    .orElseThrow(() -> new EntityNotFoundException("VehiculeRoute not found"));

            if (fromMouvements != null && !fromMouvements.isEmpty()) {
                // Clear existing FromMouvements associated with the route
                vehiculeRoute.getFromMouvements().clear();

                for (FromMouvement fromMouvement : fromMouvements) {
                    // Ensure that FromMouvement has either an Affaire or a Fournisseur, but not both
                    if (fromMouvement.getAffaire() != null && fromMouvement.getFournisseur() != null) {
                        throw new IllegalArgumentException("FromMouvement cannot have both an Affaire and a Fournisseur at the same time");
                    }

                    // Handle Affaire association
                    if (fromMouvement.getAffaire() != null) {
                        Affaire fullAffaire = affaireRepository.findById(fromMouvement.getAffaire().getId())
                                .orElseThrow(() -> new EntityNotFoundException("Affaire not found"));
                        fromMouvement.setAffaire(fullAffaire);
                    }

                    // Handle Fournisseur association
                    if (fromMouvement.getFournisseur() != null) {
                        Fournisseur fullFournisseur = fournisseurRepository.findById(fromMouvement.getFournisseur().getId())
                                .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found"));
                        fromMouvement.setFournisseur(fullFournisseur);
                    }

                    // Set the VehiculeGpsLocation and VehiculeRoute for the FromMouvement
                    fromMouvement.setVehiculeGpsLocation(vehiculeRoute.getVehiculeGpsLocation());
                    fromMouvement.setVehiculeRoute(vehiculeRoute);

                   *//* // Handle the 'to' Affaire association for this specific FromMouvement
                if (fromMouvement.getToAffaire() != null) {
                    Affaire toAffaire = affaireRepository.findById(fromMouvement.getToAffaire().getId())
                            .orElseThrow(() -> new EntityNotFoundException("To Affaire not found"));
                    fromMouvement.setToAffaire(toAffaire);
                }
*//*
                // Save and associate the FromMouvement with the VehiculeRoute
                FromMouvement savedFromMouvement = fromMouvementRepository.save(fromMouvement);
                vehiculeRoute.addFromMouvement(savedFromMouvement);
            }
        }

        // Save and return the updated VehiculeRoute
        return vehiculeRouteRepository.save(vehiculeRoute);
    }*/
//    @Transactional
//    @Override
/*
    public VehiculeRoute associateFromMouvementsAndTo(Long vehiculeRouteId, List<FromMouvementUpdateDTO> fromMouvements,String token)  {

        VehiculeRoute vehiculeRoute = vehiculeRouteRepository.findById(vehiculeRouteId)
                .orElseThrow(() -> new EntityNotFoundException("VehiculeRoute not found"));

        if (fromMouvements != null && !fromMouvements.isEmpty()) {
            vehiculeRoute.getFromMouvements().clear();

            for (FromMouvementUpdateDTO fromMouvementDTO : fromMouvements) {
                FromMouvement fromMouvement = new FromMouvement();

                if (fromMouvementDTO.getAffaire() != null && fromMouvementDTO.getFournisseur() != null) {
                    throw new IllegalArgumentException("FromMouvement cannot have both an Affaire and a Fournisseur at the same time");
                }

                if (fromMouvementDTO.getAffaire() != null) {
                    Affaire affaire = affaireService.getAffaireById(fromMouvementDTO.getAffaire().getId(),token);
                    if(affaire==null){
                        log.info("Affaire not found");
                        throw new EntityNotFoundException("Affaire not found");
                    }
                    fromMouvement.setAffaireId(affaire.getId());
                    fromMouvement.setAffaireCode(affaire.getCode());
                }

                if (fromMouvementDTO.getFournisseur() != null) {
                    Fournisseur fullFournisseur = fournisseurRepository.findById(fromMouvementDTO.getFournisseur().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found"));
                    fromMouvement.setFournisseur(fullFournisseur);
                }

                fromMouvement.setVehiculeGpsLocation(vehiculeRoute.getVehiculeGpsLocation());
                fromMouvement.setVehiculeRoute(vehiculeRoute);


                fromMouvement.setBl(fromMouvementDTO.getBl());
                fromMouvement.setBlMontant(fromMouvementDTO.getBlMontant());
                fromMouvement.setDateBl(fromMouvementDTO.getDateBl());


                FromMouvement savedFromMouvement = fromMouvementRepository.save(fromMouvement);
                vehiculeRoute.addFromMouvement(savedFromMouvement);
            }
        }

        // Save and return the updated VehiculeRoute
        return vehiculeRouteRepository.save(vehiculeRoute);
    }
*/
    //file storage
   /* public String storeFile(MultipartFile file) {
        // Define the directory where files will be uploaded
        String uploadDir = System.getProperty("user.home") + "/Apps/sr-manager/RESSOURCES/data/assets/file/";

        // Ensure the directory exists, create it if not
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException ex) {
                throw new RuntimeException("Could not create directory: " + uploadDir, ex);
            }
        }

        // Clean the file name and define the target file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = uploadPath.resolve(fileName);

        try {
            // Copy the file to the target directory, replacing any existing file with the same name
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();  // Return the full file path for storing in the entity
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file: " + fileName, ex);
        }
    }*/


    @Override
    public void deleteVehiculeroute(Long id) {
        vehiculeRouteRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> getTotalRouteLengthByVehicule() {
        List<Object[]> results = vehiculeRouteRepository.findTotalRouteLengthByVehicule();
        List<Map<String, Object>> summaryList = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> summary = new HashMap<>();
            summary.put("vehicleId", result[0]);
            summary.put("vehicleName", result[1]);
            summary.put("totalRouteLength", result[2]);
            summary.put("totalCostParVoyage", result[3]);
            summaryList.add(summary);
        }
        return summaryList;
    }

    @Override
    public List<VehiculeRoute> getPerformanceData(LocalDate startDate, LocalDate endDate) {
        List<VehiculeRoute> routes = vehiculeRouteRepository.findByDateBetween(startDate, endDate);

        // Aggregate data as needed
        return routes;
    }

    @Override
    public Map<LocalDate, Double> getPerformanceDataAsMap(LocalDate startDate, LocalDate endDate) {
        List<VehiculeRoute> routes = vehiculeRouteRepository.findByDateBetween(startDate, endDate);

        // Aggregate data
        Map<LocalDate, Double> aggregatedData = routes.stream()
                .collect(Collectors.groupingBy(
                        VehiculeRoute::getDate,
                        LinkedHashMap::new, // Use LinkedHashMap to maintain order
                        Collectors.summingDouble(VehiculeRoute::getCostPerTrip)
                ));

        // Sort by date in ascending order
        return aggregatedData.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Maintain order after sorting
                ));
    }

    @Override
    public Map<LocalDate, Double> performanceOverTime(PerformanceOverTimeRequestDTO dto) {
        List<VehiculeRoute> routes = vehiculeRouteRepository.findByDateBetween(dto.getStartDate(), dto.getEndDate());

        // Aggregate data
        Map<LocalDate, Double> aggregatedData = routes.stream()
                .collect(Collectors.groupingBy(
                        VehiculeRoute::getDate,
                        LinkedHashMap::new, // Use LinkedHashMap to maintain order
                        Collectors.summingDouble(VehiculeRoute::getCostPerTrip)
                ));

        // Sort by date in ascending order
        return aggregatedData.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Maintain order after sorting
                ));
    }
    @Override
    public List<VehiculeRoute> getAllVehiculeRoutes() {
        List<VehiculeRoute> routes = vehiculeRouteRepository.findAll();
        for (VehiculeRoute route : routes) {
            route.setFromStatus( route.getFromMouvements() != null && !route.getFromMouvements().isEmpty());
            route.setImputationStatus(route.getImputations() != null && !route.getImputations().isEmpty());
        }
        return routes;
    }

    @Override
    public List<Object[]> getTotalCostPerTripByMonth() {
        return vehiculeRouteRepository.getTotalCostPerTripByMonth();
    }

    @Override
    public Double getTotalCostForSpecificMonth(int year, int month) {
        List<Object[]> results = getTotalCostPerTripByMonth();
        for (Object[] result : results) {
            if ((Integer) result[0] == year && (Integer) result[1] == month) {
                return (Double) result[2];
            }
        }
        return 0.0;
    }

    @Override
    public Map<String, Double> calculateCostPerAffaire() {
        List<TripImputation> imputations = tripImputationRepository.findAll();

        Map<String, Double> costPerAffaire = new HashMap<>();

        for (TripImputation imputation : imputations) {
            Double cost = imputation.getCostImputation();

            costPerAffaire.merge(imputation.getAffaireCode(), cost, Double::sum);
        }

        return costPerAffaire;
    }

    //cost by group
    @Override
    public Map<String, Object> getTotalCostByGroupName() {
        List<VehiculeRoute> routes = vehiculeRouteRepository.findAll();
        List<VehiculeGpsLocation> locations = vehiculeGpsLocationRepository.findAll();

        // Map to get the group name for each vehicle
        Map<Long, String> vehicleGroupMap = new HashMap<>();
        for (VehiculeGpsLocation location : locations) {
            vehicleGroupMap.put(location.getVehicleId(), location.getGroupName());
        }

        // Map to accumulate the total cost per group
        Map<String, Double> costByGroup = new HashMap<>();
        for (VehiculeRoute route : routes) {
            Long vehicleId = route.getVehiculeGpsLocation().getVehicleId();
            String groupName = vehicleGroupMap.get(vehicleId);
            costByGroup.merge(groupName, route.getCostPerTrip(), Double::sum);
        }

        // Calculate the total cost
        double totalCost = costByGroup.values().stream().mapToDouble(Double::doubleValue).sum();

        // Map to store the cost and percentage
        Map<String, Object> result = new HashMap<>();
        Map<String, Double> percentages = new HashMap<>();

        for (Map.Entry<String, Double> entry : costByGroup.entrySet()) {
            String groupName = entry.getKey();
            double cost = entry.getValue();
            double percentage = (totalCost > 0) ? (cost / totalCost) * 100 : 0;
            percentages.put(groupName, percentage);
        }

        result.put("costByGroup", costByGroup);
        result.put("percentages", percentages);

        return result;
    }

    //--------------------------------costfiiling
    @Override
    public List<VehiculeRoute> updateFillingPercentage(UpdateFillingPercentageDTO dto) {
        try {
            // Fetch the existing VehiculeRoute by ID
            VehiculeRoute existingRoute = vehiculeRouteRepository.findById(dto.getVehiculeRouteId())
                    .orElseThrow(() -> new EntityNotFoundException("Vehicule Route not found with ID: " + dto.getVehiculeRouteId()));

            // Update the filling percentage and automatically calculate filling cost
            existingRoute.setFillingPercentage(dto.getFillingPercentage());

            if (existingRoute.getCostPerTrip() > 0 && dto.getFillingPercentage() > 0) {
                existingRoute.setFillingCost(existingRoute.getCostPerTrip() * (dto.getFillingPercentage() / 100));
            } else {
                existingRoute.setFillingCost((double) 0);
            }
            // Save and return the updated route
            vehiculeRouteRepository.save(existingRoute);

        } catch (EntityNotFoundException e) {
            log.info(e.getMessage());
        }
        return vehiculeRouteRepository.findAll();
    }


}






