package ada.cielo.prospects.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "mc_codes")
public class MCCodeEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "mc_codes_id_seq")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "program_type")
    private String programType;
}
