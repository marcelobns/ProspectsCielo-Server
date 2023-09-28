package ada.cielo.prospects.model.schemas;

import ada.cielo.prospects.model.entities.ProspectsQueueEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;

public class ProspectsQueueSchema {

    @Schema(hidden = true)
    private Long id;
    @Transient
    private Long preRegistrationId;
    @Schema(hidden = true)
    private PreRegistrationSchema preRegistration;
    @Schema(hidden = true)
    private LocalDateTime queueingAt;

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
    public PreRegistrationSchema getPreRegistration() {
        return preRegistration;
    }
    public void setPreRegistration(PreRegistrationSchema preRegistration) {
        this.preRegistration = preRegistration;
    }
    public LocalDateTime getQueueingAt() {
        return queueingAt;
    }
    public void setQueueingAt(LocalDateTime queueingAt) {
        this.queueingAt = queueingAt;
    }

    public ProspectsQueueEntity toEntity(){
        ProspectsQueueEntity prospectsQueue = new ProspectsQueueEntity();
        prospectsQueue.setId(this.getId());
        prospectsQueue.setPreRegistrationId(this.getPreRegistrationId());
        prospectsQueue.setPreRegistration(this.getPreRegistration().toEntity());
        prospectsQueue.setQueueingAt(this.getQueueingAt());

        return prospectsQueue;
    }
}
