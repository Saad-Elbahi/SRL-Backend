package ma.srmanager.sraffaires.entities.imputation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING, length = 255)
public class ChampImputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero;
    private String code;
    private String intitule;
    private Boolean imputable;

    @Column(name ="discriminator",insertable = false,updatable = false )
    private String discriminator;

  /*  @OneToOne
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompteExploitation compteExploitation;
*/
}
