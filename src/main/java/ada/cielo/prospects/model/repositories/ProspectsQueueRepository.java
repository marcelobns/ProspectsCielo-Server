package ada.cielo.prospects.model.repositories;

import ada.cielo.prospects.model.entities.ProspectsQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectsQueueRepository  extends JpaRepository<ProspectsQueueEntity, Long> {
}
