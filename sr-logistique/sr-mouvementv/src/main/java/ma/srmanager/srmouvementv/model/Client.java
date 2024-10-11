package ma.srmanager.srmouvementv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private  String  email;
    private String  ville;
    private String fiscalId;
    @Column(name = "rc")
    private String rc;

    @Column(name = "cnss")
    private String cnss;

    @Column(name = "rib")
    private String rib;
    private String grantName;
    private String gerantTelephone;
    private String gerantEmail;
}
