package ma.srmanager.srnotification;

import ma.srmanager.coreapi.base.srtools.SrToolsService;
import ma.srmanager.coreapi.base.srtools.SrToolsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class SrNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrNotificationApplication.class, args);
    }

    @Bean
    SrToolsService srToolsService(){
        return new SrToolsServiceImpl();
    }
}
