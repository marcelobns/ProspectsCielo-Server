package ada.cielo.prospects.model.schemas;

import ada.cielo.prospects.model.entities.ProspectsQueueEntity;
import jakarta.persistence.*;
import java.io.Serializable;

public class ProspectsQueueSchema implements Serializable {

    private Long id;
    private Long preRegistrationId;
    private String queueingAt;
    private String op;

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
    public String getOp() {
        return op;
    }
    public void setOp(String op) {
        this.op = op;
    }

    public ProspectsQueueEntity toEntity(){
        ProspectsQueueEntity prospectsQueue = new ProspectsQueueEntity();
        prospectsQueue.setId(this.id);
        prospectsQueue.setPreRegistrationId(this.preRegistrationId);
        prospectsQueue.setQueueingAt(this.queueingAt);
        prospectsQueue.setOp(this.op);

        return prospectsQueue;
    }
}
