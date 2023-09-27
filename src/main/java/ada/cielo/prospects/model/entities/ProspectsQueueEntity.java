package ada.cielo.prospects.model.entities;

import ada.cielo.prospects.model.schemas.ProspectsQueueSchema;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prospects_queue")
public class ProspectsQueueEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "prospects_queue_id_seq")
    private Long id;
    @Column(name = "pre_registration_id")
    private Long preRegistrationId;
    @Column(name = "queueing_at")
    private String queueingAt;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPreRegistrationId() {
        return preRegistrationId;
    }
    public void setPreRegistrationId(Long preRegistrationId) {
        this.preRegistrationId = preRegistrationId;
    }
    public String getQueueingAt() {
        return queueingAt;
    }
    public void setQueueingAt(String queueingAt) {
        this.queueingAt = queueingAt;
    }

    public ProspectsQueueSchema toSchema(){
        ProspectsQueueSchema prospectsQueue = new ProspectsQueueSchema();
        prospectsQueue.setId(this.id);
        prospectsQueue.setPreRegistrationId(this.preRegistrationId);
        prospectsQueue.setQueueingAt(this.queueingAt);

        return prospectsQueue;
    }
}
