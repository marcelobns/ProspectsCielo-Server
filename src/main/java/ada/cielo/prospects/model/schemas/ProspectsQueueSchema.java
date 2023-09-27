package ada.cielo.prospects.model.schemas;

import ada.cielo.prospects.model.entities.ProspectsQueueEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public class ProspectsQueueSchema {

    @Schema(hidden = true)
    private Long id;
    private Long preRegistrationId;
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

    public ProspectsQueueEntity toEntity(){
        ProspectsQueueEntity prospectsQueue = new ProspectsQueueEntity();
        prospectsQueue.setId(this.id);
        prospectsQueue.setPreRegistrationId(this.preRegistrationId);
        prospectsQueue.setQueueingAt(this.queueingAt);

        return prospectsQueue;
    }
}
