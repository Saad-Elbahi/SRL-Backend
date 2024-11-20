package ma.srmanager.sraffaires;

import ma.srmanager.coreapi.base.srtools.SrToolsService;
import ma.srmanager.coreapi.base.srtools.SrToolsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
public class SrAffaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrAffaireApplication.class, args);
    }


    /*@Bean
    ImportExportService importExportService(){
        return new ImportExportServiceImpl();
    }*/

    @Bean
    SrToolsService srToolsService(){
        return new SrToolsServiceImpl();
    }



}
