package ma.srmanager.srparams.entities;

import lombok.*;
import ma.srmanager.coreapi.enums.contact.Civilite;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Societe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String raisonSociele;
    private String sigle;
    private String pathLogo;
    private String email;
    private String adresse1;
    private String adresse2;
    private String codePostal;
    @ManyToOne
    private Param ville;

    private String ice;
    private String indiceFiscal;
    private String patente;
    private String rc;
    private String cnss;

    private LocalDate dateCreation;

    @OneToMany
    @ToString.Exclude
    private List<Banque> banques=new ArrayList<>();

    //Manager
    @Enumerated(EnumType.STRING)
    private Civilite civiliteManager;
    private String prenomManager;
    private String nomManager;
    private String cinManager;
    private String gsmManager;
    private String codeManager;
    private String avatarManager;
    private String emailManager;
}
