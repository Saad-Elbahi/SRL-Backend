package org.example.enginservice.openfeign;

import ma.srmanager.coreapi.base.SrUtils;

import org.example.enginservice.models.SubContractor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "SUBCONTRACTOR-QUERY", url = SrUtils.soustraitanceHost+"/subcontractors/queries")
public interface SunContractorQueryRestClient {

    @GetMapping(path = "/byId/{id}")
    Optional<SubContractor> byId(@PathVariable Long id,
                                 @RequestHeader(name = "Authorization") String token);


    @GetMapping(path = "/byProject/{projectId}")
    List<SubContractor> byProject(@PathVariable Long projectId,
                                  @RequestHeader(name = "Authorization") String token);

}
