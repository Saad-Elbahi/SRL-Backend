package ma.srmanager.srmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SrMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrMailApplication.class, args);
    }


}
