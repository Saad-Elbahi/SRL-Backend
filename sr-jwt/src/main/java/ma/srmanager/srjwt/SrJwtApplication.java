package ma.srmanager.srjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class SrJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrJwtApplication.class, args);
    }

   /* @Bean
    CommandLineRunner start(AccountCmdService accountCmdService) {
        return args -> {
            //accountService.grantPrivilegesForAdmin("6121");
            // System.out.println(accountService.initSecService());
            //accountService.initPwd("6121");
            //System.out.println(accountCmdService.backupDB());
        };
    }*/

    @Bean
    BCryptPasswordEncoder bcrypt() {
        return new BCryptPasswordEncoder();
    }


}
