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
        try {
            mcCodeRepository.save(mcCodeSchema.toEntity());
        } catch (Exception e) {
            throw new RuntimeException("Error saving MCCode: " + e.getMessage());
        }
    }

    public List<MCCodeSchema> findAll() {
        try {
            List<MCCodeEntity> mcCodes = mcCodeRepository.findAll();
            return mcCodes.stream().map(MCCodeEntity::toSchema).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding MCCode: " + e.getMessage());
        }
    }

}