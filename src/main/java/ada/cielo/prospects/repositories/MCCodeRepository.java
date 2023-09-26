package ada.cielo.prospects.repositories;

import ada.cielo.prospects.entities.MCCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MCCodeRepository  extends JpaRepository<MCCodeEntity, Long> {

}
