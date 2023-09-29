package ada.cielo.prospects.services;

import ada.cielo.prospects.model.entities.MCCodeEntity;
import ada.cielo.prospects.model.repositories.MCCodeRepository;
import ada.cielo.prospects.model.schemas.MCCodeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MCCodeService {

    private final MCCodeRepository mcCodeRepository;

    @Autowired
    public MCCodeService(MCCodeRepository mcCodeRepository) {
        this.mcCodeRepository = mcCodeRepository;
    }

    public void save(MCCodeSchema mcCodeSchema) {
        mcCodeRepository.save(mcCodeSchema.toEntity());
    }

    public List<MCCodeSchema> findAll() {
        List<MCCodeEntity> mcCodes = mcCodeRepository.findAll();
        return mcCodes.stream().map(MCCodeEntity::toSchema).toList();
    }

    public List<MCCodeSchema> findByTerm(String term){
        List<MCCodeEntity> mcCodes = mcCodeRepository.findByTerm(term);
        return mcCodes.stream().map(MCCodeEntity::toSchema).toList();
    }

    public MCCodeSchema findById(Long id) {
        MCCodeEntity mcCode = mcCodeRepository.findById(id).orElseThrow();
        return mcCode.toSchema();
    }
}