package ada.cielo.prospects.model.repositories;

import ada.cielo.prospects.model.entities.MCCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MCCodeRepository extends JpaRepository<MCCodeEntity, Long> {

    @Query(value = "SELECT * FROM mc_codes " +
                   "WHERE array_to_string(array[cast(id as text), cast(code as text), cast(program_type as text)], '|') ILIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<MCCodeEntity> findByTerm(String term);
}
