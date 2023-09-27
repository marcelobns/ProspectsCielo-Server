package ada.cielo.prospects.model.entities;

import ada.cielo.prospects.model.schemas.MCCodeSchema;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "mc_codes")
public class MCCodeEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "program_type")
    private String programType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public MCCodeSchema toSchema() {
        MCCodeSchema mcCode = new MCCodeSchema();
        mcCode.setId(this.id);
        mcCode.setCode(this.code);
        mcCode.setProgramType(this.programType);

        return mcCode;
    }
}
