package ma.srmanager.srnotification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projetName;
    private String apiUrl;
    private String classeName;
    private Long objectId;
    private String event;

    private LocalDate dateCreation;
    private LocalTime timeCreation;

    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
}
