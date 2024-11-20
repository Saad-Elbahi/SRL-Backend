package ma.srmanager.srnotification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.srmanager.coreapi.enums.notification.NotifAction;
import ma.srmanager.coreapi.enums.notification.NotifImportanceLevel;
import ma.srmanager.coreapi.enums.notification.NotifReadStatus;
import ma.srmanager.coreapi.jwt.TypeRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero;
    private int mois;
    private int annee;
    private LocalDate dateCreation;
    private LocalTime timeCreation;

    private Long destinationId;
    private String destinationUsername;
    private String destinationRole;
    private String destinationFullName;

    private Long sourceId;
    private String sourceUsername;
    private String sourceRole;
    private String sourceFullName;

    @Enumerated(EnumType.STRING)
    private TypeRole typeRole;


    private Long ChampImputation;
    //private String marcheCode;

    private String icon;
    private String heading;
    private String message;

    @Enumerated(EnumType.STRING)
    private NotifReadStatus notifReadStatus;

    @Enumerated(EnumType.STRING)
    private NotifImportanceLevel notifImportanceLevel;

    @Enumerated(EnumType.STRING)
    private NotifAction notifAction;
    private String url;
    private String route;


}
