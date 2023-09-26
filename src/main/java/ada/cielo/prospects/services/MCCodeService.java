package ada.cielo.prospects.services;

import ada.cielo.prospects.entities.MCCodeEntity;
import ada.cielo.prospects.repositories.MCCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MCCodeService {

    @Autowired
    private MCCodeRepository mcCodeRepository;

    public void save(MCCodeEntity mcCode) {
        try {
            mcCodeRepository.save(mcCode);
        } catch (Exception e) {
            throw new RuntimeException("Error saving MCCode: " + e.getMessage());
        }
    }

    public List<MCCodeEntity> findAll() {
        try {
            return mcCodeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error finding MCCode: " + e.getMessage());
        }
    }

}