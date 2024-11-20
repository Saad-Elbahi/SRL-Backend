package ma.srmanager.srnotification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.srmanager.coreapi.enums.notification.DemandeStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero;
    private int mois;
    private int annee;
    private LocalDate dateCreation;
    private LocalDate dateUpdatedStatus;

    private Long destinationId;
    private String destinationUsername;
    private String destinationRole;
    private String destinationFullName;

    private Long sourceId;
    private String sourceUsername;
    private String sourceRole;
    private String sourceFullName;

    private String message;

    @Enumerated(EnumType.STRING)
    private DemandeStatus demandeStatus;

}
