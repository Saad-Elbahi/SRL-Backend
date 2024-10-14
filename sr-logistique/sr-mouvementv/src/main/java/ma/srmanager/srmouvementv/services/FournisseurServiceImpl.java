package ma.srmanager.srmouvementv.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srmouvementv.dto.FournisseurDTO;
import ma.srmanager.srmouvementv.model.Fournisseur;
import ma.srmanager.srmouvementv.repositories.FournisseurRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    private FournisseurDTO convertToDto(Fournisseur fournisseur) {
        FournisseurDTO dto = new FournisseurDTO();
        dto.setId(fournisseur.getId());
        dto.setAbbreviationFournisseur(fournisseur.getAbbreviationFournisseur());
        dto.setIntituleFournisseur(fournisseur.getIntituleFournisseur());
        dto.setAdresseFournisseur(fournisseur.getAdresseFournisseur());
        dto.setIce(fournisseur.getIce());
        dto.setEmail(fournisseur.getEmail());
        dto.setTelephone(fournisseur.getTelephone());
        dto.setContact(fournisseur.getContact());
        return dto;
    }

    private Fournisseur convertToEntity(FournisseurDTO dto) {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(dto.getId());
        fournisseur.setAbbreviationFournisseur(dto.getAbbreviationFournisseur());
        fournisseur.setIntituleFournisseur(dto.getIntituleFournisseur());
        fournisseur.setAdresseFournisseur(dto.getAdresseFournisseur());
        fournisseur.setIce(dto.getIce());
        fournisseur.setEmail(dto.getEmail());
        fournisseur.setTelephone(dto.getTelephone());
        fournisseur.setContact(dto.getContact());
        return fournisseur;
    }

    @Override
    public FournisseurDTO createFournisseur(FournisseurDTO fournisseurDTO) {
        Fournisseur fournisseur = convertToEntity(fournisseurDTO);
        Fournisseur savedFournisseur = fournisseurRepository.save(fournisseur);
        return convertToDto(savedFournisseur);
    }

    @Override
    public FournisseurDTO updateFournisseur(Long id, FournisseurDTO fournisseurDTO) {
        Optional<Fournisseur> fournisseurOpt = fournisseurRepository.findById(id);
        if (fournisseurOpt.isPresent()) {
            Fournisseur fournisseur = fournisseurOpt.get();
            fournisseur.setAbbreviationFournisseur(fournisseurDTO.getAbbreviationFournisseur());
            fournisseur.setIntituleFournisseur(fournisseurDTO.getIntituleFournisseur());
            fournisseur.setAdresseFournisseur(fournisseurDTO.getAdresseFournisseur());
            fournisseur.setIce(fournisseurDTO.getIce());
            fournisseur.setEmail(fournisseurDTO.getEmail());
            fournisseur.setTelephone(fournisseurDTO.getTelephone());
            fournisseur.setContact(fournisseurDTO.getContact());
            return convertToDto(fournisseurRepository.save(fournisseur));
        }
        return null;
    }

    @Override
    public List<FournisseurDTO> getAllFournisseurs() {
        return fournisseurRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FournisseurDTO> getFournisseurById(FournisseurDTO fournisseurDTO) {
        return fournisseurRepository.findById(fournisseurDTO.getId()).map(this::convertToDto);
    }

    @Override
    public void deleteFournisseur(FournisseurDTO fournisseurDTO) {
        fournisseurRepository.deleteById(fournisseurDTO.getId());
    }

    @Override
    public void uploadFournisseurs(MultipartFile file) throws IOException {
        if (!isValidExcelFile(file)) {
            throw new IllegalArgumentException("Invalid file type. Please upload an Excel file.");
        }

        List<Fournisseur> fournisseurs = getFournisseursDataFromExcel(file.getInputStream());
        fournisseurRepository.saveAll(fournisseurs);
    }

    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Fournisseur> getFournisseursDataFromExcel(InputStream inputStream) {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (rowIndex == 0) {
                    continue; // Skip header row
                }

                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setAbbreviationFournisseur(getStringCellValue(row.getCell(0)));
                fournisseur.setIntituleFournisseur(getStringCellValue(row.getCell(1)));
                fournisseur.setAdresseFournisseur(getStringCellValue(row.getCell(2)));
                fournisseur.setIce(getStringCellValue(row.getCell(3)));
                fournisseur.setEmail(getStringCellValue(row.getCell(4)));
                fournisseur.setTelephone(getStringCellValue(row.getCell(5)));
                fournisseur.setContact(getStringCellValue(row.getCell(6)));

                fournisseurs.add(fournisseur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fournisseurs;
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null) return null;
        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : cell.toString();
    }




//    private ObjectMapper objectMapper;
//    private RestTemplate restTemplate;

    /*@Override
    public List<Fournisseur> saveFournisseurFromApi() throws IOException {
        String apiUrl = "https://rouandigps.com/api/api.php?api=user&key=88918E46B26489F0ECDC7966541FE2A9&cmd=USER_GET_ZONES";
        //  HTTP GET request to the API
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        // Parse JSON response
        JsonNode jsonData = objectMapper.readTree(jsonResponse);
        List<Fournisseur> fournisseurs = new ArrayList<>();
        // map JSON data  to Fournisseur objects
        for (JsonNode fournisseurData : jsonData) {
            String groupName = fournisseurData.get("group_name").asText();
            if ("FOURNISSEUR".equals(groupName)) {
                String name = fournisseurData.get("name").asText();
                List<Fournisseur> existingFournisseur = fournisseurRepository.findByNameAndGroupName(name, groupName);
                if (existingFournisseur.isEmpty()) {
                    Fournisseur fournisseur = new Fournisseur();
                    fournisseur.setGroupName(fournisseurData.get("group_name").asText());
                    fournisseur.setName(fournisseurData.get("name").asText());
                    fournisseur.setVertices(fournisseurData.get("vertices").asText());
                    fournisseurs.add(fournisseur);
                }
            }
        }
        // Save Affaire  to the database
        fournisseurRepository.saveAll(fournisseurs);
        return fournisseurRepository.findAll();
    }*/


}
