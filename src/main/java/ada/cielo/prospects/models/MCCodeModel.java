package ada.cielo.prospects.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "mc_codes")
public class MCCodeModel implements Serializable {

    @Id
    @GeneratedValue(generator = "mc_codes_id_seq")
    private Long id;
    private String code;
    private String programType;

}
