package ada.cielo.prospects.model.repositories;

import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreRegistrationRepository  extends JpaRepository<PreRegistrationEntity, Long> {
}
