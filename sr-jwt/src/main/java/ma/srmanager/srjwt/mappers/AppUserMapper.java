package ma.srmanager.srjwt.mappers;

import ma.srmanager.srjwt.dtos.AppUserResponseDTO;
import ma.srmanager.srjwt.entities.AppUser;

@org.mapstruct.Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserResponseDTO entityToResponseDto(AppUser appUser);
}
