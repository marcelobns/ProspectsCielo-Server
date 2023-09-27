package ada.cielo.prospects.model.repositories;

import ada.cielo.prospects.model.entities.ProspectsQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectsQueueRepository  extends JpaRepository<ProspectsQueueEntity, Long> {

    @Query(value = "SELECT * FROM prospects_queue ORDER BY queueing_at ASC, id ASC LIMIT 1", nativeQuery = true)
    ProspectsQueueEntity getNext();
}
