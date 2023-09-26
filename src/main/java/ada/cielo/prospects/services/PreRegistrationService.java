package ada.cielo.prospects.services;

import ada.cielo.prospects.model.repositories.PreRegistrationRepository;
import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreRegistrationService {

    private final PreRegistrationRepository preRegistrationsRepository;

    @Autowired
    public PreRegistrationService(PreRegistrationRepository preRegistrationsRepository) {
        this.preRegistrationsRepository = preRegistrationsRepository;
    }

    public void save(PreRegistrationSchema preRegistrationSchema) {
        try {
            preRegistrationsRepository.save(preRegistrationSchema.toEntity());
        } catch (Exception e) {
            throw new RuntimeException("Error saving MCCode: " + e.getMessage());
        }
    }

    public List<PreRegistrationSchema> findAll() {
        try {
            List<PreRegistrationEntity> preRegistrationEntityList = preRegistrationsRepository.findAll();
            return preRegistrationEntityList.stream().map(PreRegistrationEntity::toSchema).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding MCCode: " + e.getMessage());
        }
    }

}