package ma.srmanager.srjwt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.srmanager.coreapi.jwt.TypeRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
public class AppRoleResponseDTO {
    private Long id;
    private String roleName;
    @Enumerated(EnumType.STRING)
    private TypeRole typeRole;
}
