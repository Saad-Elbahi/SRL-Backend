package ma.srmanager.srparams.openfeign;

import ma.srmanager.coreapi.base.SrUtils;
import ma.srmanager.coreapi.pointage.CreateDeptBioTimeDTO;
import ma.srmanager.srparams.models.DepartmentBioTime;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "BioTimeCmd", url = SrUtils.pointageBioTimeHost + "/personnel/api")
public interface PersonnelBioTimeCmdRestClient {


    @PostMapping(value = "/departments/", consumes = "application/json")
    DepartmentBioTime createDept(@RequestBody CreateDeptBioTimeDTO dto,
                                 @RequestHeader(name = "Content-Type") String ContentType,
                                 @RequestHeader(name = "Authorization") String bioTimeToken);



}
