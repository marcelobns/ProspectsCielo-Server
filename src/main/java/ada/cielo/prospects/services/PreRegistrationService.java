package ada.cielo.prospects.services;

import ada.cielo.prospects.model.entities.MCCodeEntity;
import ada.cielo.prospects.model.repositories.PreRegistrationRepository;
import ada.cielo.prospects.model.schemas.MCCodeSchema;
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

    public PreRegistrationSchema save(PreRegistrationSchema preRegistrationSchema) {
        return preRegistrationsRepository.save(preRegistrationSchema.toEntity()).toSchema();
    }


    public PreRegistrationSchema findOne(Long id) {
        PreRegistrationEntity preRegistrationEntity = preRegistrationsRepository.findById(id).orElseThrow();
        return preRegistrationEntity.toSchema();
    }

    public List<PreRegistrationSchema> findByTerm(String term){
        List<PreRegistrationEntity> preRegistrationEntityList = preRegistrationsRepository.findByTerm(term);
        return preRegistrationEntityList.stream().map(PreRegistrationEntity::toSchema).toList();
    }

    public PreRegistrationSchema delete(Long id) {
        PreRegistrationEntity preRegistrationEntity = preRegistrationsRepository.findById(id).orElseThrow();
        preRegistrationsRepository.delete(preRegistrationEntity);
        return preRegistrationEntity.toSchema();
    }

}