package ada.cielo.prospects.model.schemas;

import ada.cielo.prospects.model.entities.PreRegistrationEntity;

public class PreRegistrationSchema {

    private Long id;
    private String registrationType;
    private Long mcCodeId;
    private String email;
    private String name;
    private String attributes;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRegistrationType() {
        return registrationType;
    }
    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }
    public Long getMcCodeId() {
        return mcCodeId;
    }
    public void setMcCodeId(Long mcCodeId) {
        this.mcCodeId = mcCodeId;
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

    public PreRegistrationEntity toEntity() {
        PreRegistrationEntity preRegistration = new PreRegistrationEntity();
        preRegistration.setId(this.id);
        preRegistration.setRegistrationType(this.registrationType);
        preRegistration.setMcCodeId(this.mcCodeId);
        preRegistration.setEmail(this.email);
        preRegistration.setName(this.name);
        preRegistration.setAttributes(this.attributes);

        return preRegistration;
    }
}
