package ada.cielo.prospects.model.entities;

import ada.cielo.prospects.model.schemas.ProspectsQueueSchema;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "prospects_queue")
public class ProspectsQueueEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "queueing_at")
    private LocalDateTime queueingAt;
    @Transient
    private Long preRegistrationId;
    @ManyToOne
    @JoinColumn(name = "pre_registration_id", referencedColumnName = "id")
    private PreRegistrationEntity preRegistration;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPreRegistrationId() {return preRegistrationId;}
    public void setPreRegistrationId(Long preRegistrationId) {
        this.preRegistrationId = preRegistrationId;

    }
    public PreRegistrationEntity getPreRegistration() {return preRegistration;}
    public void setPreRegistration(PreRegistrationEntity preRegistration) {this.preRegistration = preRegistration;}
    public LocalDateTime getQueueingAt() {
        return queueingAt;
    }
    public void setQueueingAt(LocalDateTime queueingAt) {
        this.queueingAt = queueingAt;
    }

    public ProspectsQueueSchema toSchema(){
        ProspectsQueueSchema prospectsQueue = new ProspectsQueueSchema();
        prospectsQueue.setId(this.getId());
        prospectsQueue.setPreRegistrationId(this.getPreRegistration().getId());
        prospectsQueue.setPreRegistration(this.getPreRegistration().toSchema());
        prospectsQueue.setQueueingAt(this.getQueueingAt());

        return prospectsQueue;
    }
}
