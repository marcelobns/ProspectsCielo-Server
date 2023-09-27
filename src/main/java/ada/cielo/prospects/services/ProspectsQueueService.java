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

    public ProspectsQueueSchema save(ProspectsQueueSchema prospectsQueueSchema) {
        return prospectsQueueRepository.save(prospectsQueueSchema.toEntity()).toSchema();
    }

    public List<ProspectsQueueSchema> findByTerm(String term) {
        List<ProspectsQueueEntity> prospectsQueueEntityList = prospectsQueueRepository.findByTerm(term);
        return prospectsQueueEntityList.stream().map(ProspectsQueueEntity::toSchema).toList();
    }

    public ProspectsQueueSchema getNext() {
        return prospectsQueueRepository.getNext().toSchema();
    }

    public ProspectsQueueSchema delete(Long id) {
        ProspectsQueueEntity prospectsQueueEntity = prospectsQueueRepository.findById(id).orElseThrow();
        prospectsQueueRepository.delete(prospectsQueueEntity);
        return prospectsQueueEntity.toSchema();
    }

}