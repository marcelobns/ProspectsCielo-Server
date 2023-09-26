package ada.cielo.prospects.model.entities;

import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "pre_registrations")
public class PreRegistrationEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "pre_registrations_id_seq")
    private Long id;
    @Column(name = "registration_type")
    private String registrationType;
    @Column(name = "mc_code_id")
    private Long mcCodeId;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "attributes")
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

    public PreRegistrationSchema toSchema() {
        PreRegistrationSchema preRegistration = new PreRegistrationSchema();
        preRegistration.setId(this.id);
        preRegistration.setRegistrationType(this.registrationType);
        preRegistration.setMcCodeId(this.mcCodeId);
        preRegistration.setEmail(this.email);
        preRegistration.setName(this.name);
        preRegistration.setAttributes(this.attributes);

        return preRegistration;
    }
}
