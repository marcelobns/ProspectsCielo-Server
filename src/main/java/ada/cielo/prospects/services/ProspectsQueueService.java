package ada.cielo.prospects.services;

import ada.cielo.prospects.model.entities.ProspectsQueueEntity;
import ada.cielo.prospects.model.repositories.ProspectsQueueRepository;
import ada.cielo.prospects.model.schemas.ProspectsQueueSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectsQueueService {

    private final ProspectsQueueRepository prospectsQueueRepository;

    @Autowired
    public ProspectsQueueService(ProspectsQueueRepository prospectsQueueRepository) {
        this.prospectsQueueRepository = prospectsQueueRepository;
    }

    public void save(ProspectsQueueSchema prospectsQueueSchema) {
        try {
            prospectsQueueRepository.save(prospectsQueueSchema.toEntity());
        } catch (Exception e) {
            throw new RuntimeException("Error saving MCCode: " + e.getMessage());
        }
    }

    public List<ProspectsQueueSchema> findAll() {
        try {
            List<ProspectsQueueEntity> prospectsQueueEntityList = prospectsQueueRepository.findAll();
            return prospectsQueueEntityList.stream().map(ProspectsQueueEntity::toSchema).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding MCCode: " + e.getMessage());
        }
    }

    public ProspectsQueueSchema getNext() {
        return prospectsQueueRepository.getNext().toSchema();
    }

}