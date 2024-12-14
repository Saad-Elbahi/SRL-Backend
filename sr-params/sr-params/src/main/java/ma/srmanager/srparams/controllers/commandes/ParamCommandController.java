package ma.srmanager.srparams.controllers.commandes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.base.SrUtils;
import ma.srmanager.coreapi.importation
        .ImportExportService;
import ma.srmanager.coreapi.params.dtos.CreateParamRequestDTO;
import ma.srmanager.coreapi.params.dtos.UpdateParamRequestDTO;
import ma.srmanager.srparams.entities.Param;
import ma.srmanager.srparams.services.commandes.ParamCmdService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/params/commands")
@AllArgsConstructor
public class ParamCommandController {


    private final ImportExportService importExportService;
    private final ParamCmdService paramCmdService;



    @PostMapping("/create")
    public Param create(@RequestBody CreateParamRequestDTO dto) {
        return paramCmdService.create(dto);
    }

    @PostMapping("/update")
    public Param update(@RequestBody UpdateParamRequestDTO dto) {
        return paramCmdService.update(dto);
    }

    @GetMapping("/import/{type}")
    public String imports(@PathVariable String type) throws IOException, ParseException,
            InvalidFormatException, ClassNotFoundException, InvocationTargetException,
            IllegalAccessException, InstantiationException {

         String fileName = SrUtils.FOLDER_PATH + "/PARAMS/data.xlsx";

        List<CreateParamRequestDTO> list = importExportService.importer("CreateParamRequestDTO", fileName);
        //paysRepository.deleteAll();
        AtomicInteger i = new AtomicInteger();
        list.forEach(dto -> {
            if (dto.getParamType().toString().equalsIgnoreCase(type)) {
                log.info("+++++++++ imports +++++++++++");
                log.info(dto.getParamType().toString());
                log.info("+++++++++ imports +++++++++++");
                i.getAndIncrement();
                paramCmdService.create(dto);
            }
        });

        return "Importation réussie: " + i+ " "+ type  +" importés...";
    }



}
