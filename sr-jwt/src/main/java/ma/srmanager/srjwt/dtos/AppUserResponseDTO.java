package ma.srmanager.srjwt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.srmanager.srjwt.entities.AppRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
public class AppUserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String email;

    private String avatar;
    private String role;
    private String accessToken;
    private String refreshToken;

    private List<AppRole> appRoles = new ArrayList<>();

    private boolean actived;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private Date date;
    private String workingDays;
    private  boolean sessionExpired;



}
