package ma.srmanager.srjwt.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String avatar;
    private String role;
    private String accessToken;
    private String refreshToken;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> appRoles = new ArrayList<>();

    private boolean actived;
    private String email;
    @Transient
    private String oldPassword;

    private  boolean accountNonLocked;
    private  boolean accountNonExpired;
    private  boolean credentialsNonExpired;
    private  boolean enabled;

    private  String workingDays;
    private  boolean sessionExpired;

}
