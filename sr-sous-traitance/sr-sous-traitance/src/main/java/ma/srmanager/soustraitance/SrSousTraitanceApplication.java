package ma.srmanager.soustraitance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;


@SpringBootApplication
@EnableFeignClients
@EnableScheduling
//@EnableDiscoveryClient
public class SrSousTraitanceApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Africa/Casablanca"));
        //TimeZone.setDefault(TimeZone.getTimeZone("GMT+01:00"));
        SpringApplication.run(SrSousTraitanceApplication.class, args);
    }


  /*  @Bean
    SrToolsService srToolsService() {
        return new SrToolsServiceImpl();
    }

    @Bean
    ImportExportService importExportService(){
        return new ImportExportServiceImpl();
    }

    @Bean
    CommandLineRunner start(EngagementCmdService engagementCmdService) {
        return args -> {


            CreateSubContractorPaiementDTO dto=new CreateSubContractorPaiementDTO();
*/
            /*
                    var attachementId: Long = 0,
                    var situationSubContractorId: Long = 0,
                    var sousTraitanceId: Long = 0,
                    var subContractorId: Long = 0,

                    var date: LocalDate = LocalDate.now(),
                    var dateEcheance: LocalDate = LocalDate.now(),

                    var day: Int = 0,
                    var month: Int = 0,
                    var year: Int = 0,

                    var delaiJour: Int = 0,
                    var delaiMois: Int = 0,

                    var intitule: String? = "",
                    var reference: String? = "",
                    var moyenPaiement: MoyenPaiement = MoyenPaiement.VIREMENT,
                    var motifReglement: MotifReglement = MotifReglement.HISTORIQUE,
                    var accountMovementStatus: AccountMovementStatus? = AccountMovementStatus.AWAITING_TREATMENT,
                    var amount: Double = 0.0,
                    var token: String = "",

                    var subContractorFilterId: Long = 0,
                    var yearFilter: Int = 0,
                    var monthFilter: Int = 0,
             */


            /*Locale locale = new Locale("fr", "ma");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
            symbols.setDecimalSeparator('.');
            symbols.setGroupingSeparator(' ');

            String pattern = "#,##0.00";
            DecimalFormat formatter = new DecimalFormat(pattern, symbols);

            String number = formatter.format(3456789.125);
            System.out.println(number);*/

        };


