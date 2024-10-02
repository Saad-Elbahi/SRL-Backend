package ma.srmanager.sreurikaserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SrEurikaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrEurikaServerApplication.class, args);
    }

}
