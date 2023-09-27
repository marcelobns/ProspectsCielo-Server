package ada.cielo.prospects.model.repositories;

import ada.cielo.prospects.model.entities.MCCodeEntity;
import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreRegistrationRepository  extends JpaRepository<PreRegistrationEntity, Long> {

    @Query(value = "SELECT * FROM pre_registrations " +
                   "WHERE array_to_string(array[cast(id as text), cast(registration_type as text), cast(document_number as text), cast(name as text), cast(email as text), cast(attributes as text), cast(mc_code_id as text), cast(op as text), cast(at as text)], '|')  ILIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<PreRegistrationEntity> findByTerm(String term);
}
