package ma.srmanager.srparams;

import ma.srmanager.coreapi.base.srtools.SrToolsService;
import ma.srmanager.coreapi.base.srtools.SrToolsServiceImpl;
import ma.srmanager.coreapi.importation.ImportExportService;
import ma.srmanager.coreapi.importation.ImportExportServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SrParamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrParamsApplication.class, args);
    }

    @Bean
    ImportExportService importExportService(){
        return new ImportExportServiceImpl();
    }

    @Bean
    SrToolsService srToolsService(){
        return new SrToolsServiceImpl();
    }


}
