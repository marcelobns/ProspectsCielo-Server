package ada.cielo.prospects.services;

import ada.cielo.prospects.model.repositories.PreRegistrationRepository;
import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<PreRegistrationSchema> findByTerm(String term, String order){
        if (term == null) term = "%";
        if (order == null) order = "id asc";

        String orderField = order.split(" ")[0];
        String orderDirection = order.split(" ")[1];
        Sort.Direction direction = orderDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, orderField);

        List<PreRegistrationEntity> preRegistrationEntityList = preRegistrationsRepository.findByTerm(term, sort);
        return preRegistrationEntityList.stream().map(PreRegistrationEntity::toSchema).toList();
    }

    public PreRegistrationSchema delete(Long id) {
        PreRegistrationEntity preRegistrationEntity = preRegistrationsRepository.findById(id).orElseThrow();
        preRegistrationsRepository.delete(preRegistrationEntity);
        return preRegistrationEntity.toSchema();
    }

}