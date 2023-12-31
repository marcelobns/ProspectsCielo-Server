package ada.cielo.prospects.model.schemas;

import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import ada.cielo.prospects.services.MCCodeService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PreRegistrationSchema {

    @Schema(hidden = true)
    private Long id;
    private String registrationType;
    private String documentNumber;
    private String email;
    private String name;
    private String attributes;
    private String status;
    private Long mcCodeId;
    @Schema(hidden = true)
    private MCCodeSchema mcCode;
    @Schema(hidden = true)
    private String op;
    @Schema(hidden = true)
    private LocalDateTime at;

    private String observation;

    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id;}
    public String getRegistrationType() {
        return registrationType;
    }
    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAttributes() {
        return attributes;
    }
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public Long getMcCodeId() {return mcCodeId;}
    public void setMcCodeId(Long mcCodeId) {this.mcCodeId = mcCodeId;}
    public MCCodeSchema getMcCode() {return this.mcCode;}
    public void setMcCode(MCCodeSchema mcCode) {
        this.mcCode = mcCode;
    }
    public String getOp() {
        return op;
    }
    public void setOp(String op) {
        this.op = op;
    }
    public LocalDateTime getAt() {
        return at;
    }
    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    public String getObservation() {return observation;}
    public void setObservation(String observation) {this.observation = observation;}

    public PreRegistrationEntity toEntity() {
        PreRegistrationEntity preRegistration = new PreRegistrationEntity();
        preRegistration.setId(this.id);
        preRegistration.setRegistrationType(this.registrationType);
        preRegistration.setDocumentNumber(this.documentNumber);
        preRegistration.setEmail(this.email);
        preRegistration.setName(this.name);
        preRegistration.setAttributes(this.attributes);
        preRegistration.setStatus(this.status);
        preRegistration.setOp(this.op);
        preRegistration.setAt(this.at);

        preRegistration.setMcCodeId(this.mcCodeId);
        preRegistration.setMcCode(this.mcCode.toEntity());

        preRegistration.setObservation(this.observation);

        return preRegistration;
    }

    public void validate() {

        String documentNumberSanitized = null;
        if(this.documentNumber != null )
            documentNumberSanitized = this.documentNumber.replaceAll("[./-]", "");

        if (this.name != null && (this.name.length() < 3 || this.name.length() > 50)) {
            throw new IllegalArgumentException("Nome deve ter no máximo 50 caracteres");
        }
        if (this.email != null && !this.email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if ("pessoa_juridica".equals(this.registrationType)) {
            if (documentNumberSanitized != null && documentNumberSanitized.length() != 14) {
                throw new IllegalArgumentException("CNPJ deve ter 14 dígitos");
            }

        } else if ("pessoa_fisica".equals(this.registrationType)) {
            if (documentNumberSanitized != null && documentNumberSanitized.length() != 11) {
                throw new IllegalArgumentException("CPF deve ter 11 dígitos");
            }
        } else {
            throw new IllegalArgumentException("Tipo de pessoa inválido");
        }
    }

    public PreRegistrationSchema merge(PreRegistrationSchema from, PreRegistrationSchema to) {
        if (from.getStatus() != null) {
            to.setStatus(from.getStatus());
        }
        if (from.getObservation() != null) {
            to.setObservation(from.getObservation());
        }
        return to;
    }
}
