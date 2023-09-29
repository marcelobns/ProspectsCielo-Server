package ada.cielo.prospects.model.repositories;

import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;


import java.util.List;

@Repository
public interface PreRegistrationRepository  extends JpaRepository<PreRegistrationEntity, Long> {

    @Query("SELECT p FROM PreRegistrationEntity p WHERE " +
            "CONCAT(p.id, p.registrationType, p.documentNumber, p.name, p.email, p.attributes, p.mcCode, p.op, p.at) LIKE %:term%")
    List<PreRegistrationEntity> findByTerm(@Param("term") String term, Sort sort);
}
