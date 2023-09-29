package ada.cielo.prospects.model.entities;

import ada.cielo.prospects.model.schemas.MCCodeSchema;
import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "pre_registrations")
public class PreRegistrationEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "registration_type")
    private String registrationType;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "attributes")
    private String attributes;
    @Column(name = "status")
    private String status;

    @Column(name = "op")
    private String op;
    @Column(name = "at")
    private LocalDateTime at;

    @Transient
    private Long mcCodeId;
    @ManyToOne
    @JoinColumn(name = "mc_code_id", referencedColumnName = "id")
    private MCCodeEntity mcCode;

    @Column(name = "observation")
    private String observation;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}
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
    public Long getMcCodeId() {
        return mcCodeId;
    }
    public void setMcCodeId(Long mcCodeId) {
        this.mcCodeId = mcCodeId;
    }
    public MCCodeEntity getMcCode() {
        return mcCode;
    }

    public void setMcCode(MCCodeEntity mcCode) {
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

    public PreRegistrationEntity() {
    }

    //For tests only
    public PreRegistrationEntity(Long id, String registrationType, String documentNumber, String email, String name, String attributes, String op, LocalDateTime at, MCCodeEntity mcCode) {
        this.id = id;
        this.registrationType = registrationType;
        this.documentNumber = documentNumber;
        this.email = email;
        this.name = name;
        this.attributes = attributes;
        this.op = op;
        this.at = at;
        this.mcCode = mcCode;
    }

    public PreRegistrationSchema toSchema() {
        PreRegistrationSchema preRegistration = new PreRegistrationSchema();
        preRegistration.setId(this.getId());
        preRegistration.setDocumentNumber(this.getDocumentNumber());
        preRegistration.setRegistrationType(this.getRegistrationType());
        preRegistration.setMcCodeId(this.getMcCode().getId());
        preRegistration.setMcCode(this.getMcCode().toSchema());
        preRegistration.setEmail(this.getEmail());
        preRegistration.setName(this.getName());
        preRegistration.setAttributes(this.getAttributes());
        preRegistration.setStatus(this.getStatus());
        preRegistration.setOp(this.getOp());
        preRegistration.setAt(this.getAt());
        preRegistration.setObservation(this.getObservation());

        return preRegistration;
    }
}
