package ada.cielo.prospects.model.repositories;

import ada.cielo.prospects.model.entities.MCCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MCCodeRepository extends JpaRepository<MCCodeEntity, Long> {

}
