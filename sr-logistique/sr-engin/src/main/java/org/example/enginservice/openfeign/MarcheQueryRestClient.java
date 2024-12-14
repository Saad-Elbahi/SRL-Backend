package org.example.enginservice.openfeign;


import ma.srmanager.srjwt.coreapi.base.SrUtils;
import org.example.enginservice.models.Marche;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "SR-MARCHES", url = SrUtils.affaireHost +"/marches/queries")
public interface MarcheQueryRestClient {


    @GetMapping(path = "/byId/{id}")
    Optional<Marche> byId(@PathVariable Long id,
                          @RequestHeader(name = "Authorization") String token);


    @GetMapping("/byStatus/{status}")
    List<Marche> byStatus(@PathVariable String status,
                                 @RequestHeader(name = "Authorization") String token);


}
