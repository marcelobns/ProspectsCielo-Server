package ada.cielo.prospects.model.schemas;

import ada.cielo.prospects.model.entities.MCCodeEntity;

public class MCCodeSchema {
    private Long id;
    private String code;
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

    public MCCodeEntity toEntity() {
        MCCodeEntity mcCode = new MCCodeEntity();
        mcCode.setId(this.id);
        mcCode.setCode(this.code);
        mcCode.setProgramType(this.programType);

        return mcCode;
    }
}
