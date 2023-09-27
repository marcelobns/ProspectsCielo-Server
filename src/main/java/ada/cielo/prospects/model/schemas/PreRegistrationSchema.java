package ada.cielo.prospects.model.schemas;

import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class PreRegistrationSchema {

    @Schema(hidden = true)
    private Long id;
    private String registrationType;
    private String documentNumber;
    private String email;
    private String name;
    private String attributes;
    private Long mcCodeId;
    @Schema(hidden = true)
    private MCCodeSchema mcCode;
    @Schema(hidden = true)
    private String op;
    @Schema(hidden = true)
    private LocalDateTime at;

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
    public Long getMcCodeId() {
        return mcCodeId;
    }
    public void setMcCodeId(Long mcCodeId) {
        this.mcCodeId = mcCodeId;
    }
    public MCCodeSchema getMcCode() {
        return mcCode;
    }
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

    public PreRegistrationEntity toEntity() {
        PreRegistrationEntity preRegistration = new PreRegistrationEntity();
        preRegistration.setId(this.getId());
        preRegistration.setRegistrationType(this.getRegistrationType());
        preRegistration.setDocumentNumber(this.getDocumentNumber());
        preRegistration.setMcCodeId(this.getMcCode().getId());
        preRegistration.setMcCode(this.getMcCode().toEntity());
        preRegistration.setEmail(this.getEmail());
        preRegistration.setName(this.getName());
        preRegistration.setAttributes(this.getAttributes());
        preRegistration.setOp(this.getOp());
        preRegistration.setAt(this.getAt());

        return preRegistration;
    }

    public void validate() {
        if (this.name == null || this.name.length() < 3 || this.name.length() > 100) {
            throw new IllegalArgumentException("Nome deve ter no máximo 100 caracteres");
        }
        if (email == null || !email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if ("pessoa_juridica".equals(this.registrationType)) {
            if (this.documentNumber == null || this.documentNumber.length() != 14) {
                throw new IllegalArgumentException("CNPJ deve ter 14 dígitos");
            }

        } else if ("pessoa_fisica".equals(this.registrationType)) {
            if (this.documentNumber == null || this.documentNumber.length() != 11) {
                throw new IllegalArgumentException("CPF deve ter 11 dígitos");
            }
        } else {
            throw new IllegalArgumentException("Tipo de pessoa inválido");
        }
    }
}
