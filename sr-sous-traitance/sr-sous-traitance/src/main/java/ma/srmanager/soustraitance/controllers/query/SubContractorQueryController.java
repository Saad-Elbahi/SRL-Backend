package ma.srmanager.soustraitance.controllers.query;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.soustraitance.entities.subcontractor.SubContractor;
import ma.srmanager.soustraitance.services.query.SubContractorQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RequestMapping("/subcontractors/queries")
public class SubContractorQueryController {

    private SubContractorQueryService subContractorQueryService;
 //   private DeductionQueryService deductionQueryService;

    //private QueryGateway queryGateway;

    /*@GetMapping("/byType/{type}")
    public CompletableFuture<List<SubContractor>> contactsByType(@PathVariable String type){
        CompletableFuture<List<SubContractor>> subContractors= queryGateway.query(new GetAllSubContractorByTypeQuery(SubContractorType.valueOf(type.toUpperCase())),
                ResponseTypes.multipleInstancesOf(SubContractor.class));
        System.out.println(subContractors);
        return subContractors;

    }*/

   /* @GetMapping("/all")
    public List<SubContractor> getAll(@RequestHeader(name = "Authorization") String token) {
        return subContractorQueryService.getAll(token);
    }*/
    @GetMapping("/byProject/{projectId}")
    public List<SubContractor> byProject(@PathVariable Long projectId) {
        log.info("subcontractor query byProject {}", projectId);
        return subContractorQueryService.byProject(projectId);
    }


  /*  @GetMapping("/export/{format}")
    public SrResponseMessage export(@PathVariable String format,
                                    @RequestHeader(name = "Authorization") String token)
            throws JRException, FileNotFoundException {
        log.info("export=>step 0");
        return subContractorQueryService.export(format,token);

    }

    @GetMapping("/getAllDebtors")
    public List<SubContractor> getAllDebtors(@RequestHeader(name = "Authorization") String token){
        *//*List<SubContractor>subContractors= queryGateway.query(
                new GetAllDebtorsQuery(),
                ResponseTypes.multipleInstancesOf(SubContractor.class)).join();*//*
        return subContractorQueryService.getAllDebtors(token);
    }
*/

    @GetMapping("/byId/{id}")
    public SubContractor contactsById(@PathVariable Long id,@RequestHeader(name = "Authorization") String token){
       /* return queryGateway.query(new GetSubContractorByIdQuery(id),
                ResponseTypes.instanceOf(SubContractor.class)).join();*/
        return subContractorQueryService.byId(id,token);
    }

}
