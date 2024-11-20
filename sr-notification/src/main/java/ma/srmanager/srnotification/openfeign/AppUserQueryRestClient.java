package ma.srmanager.srnotification.openfeign;

import ma.srmanager.coreapi.base.SrUtils;
import ma.srmanager.coreapi.jwt.AppUserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "SR-JWT", url = SrUtils.authHost + "/jwt/queries")
public interface AppUserQueryRestClient {

    @GetMapping(path = "/usersByRole/{role}", consumes = "application/json")
    List<AppUserResponseDTO> usersByRole(@PathVariable(name = "role") String role,
                                         @RequestHeader(name = "Authorization") String token);

    @GetMapping(value = "/getOneUseById/{id}", consumes = "application/json")
    AppUserResponseDTO getOneUseById(@PathVariable(name = "id") Long id,
                                     @RequestHeader(name = "Authorization") String token);

    @GetMapping(value = "/getUserByUserName/{username}", consumes = "application/json")
    AppUserResponseDTO getUserByUserName(@PathVariable(name = "username") String username,
                                     @RequestHeader(name = "Authorization") String token);

    @GetMapping(value = "/profile", consumes = "application/json")
    AppUserResponseDTO profile(@RequestHeader(name = "Authorization") String token);
}
