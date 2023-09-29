package ada.cielo.prospects.model.repositories;

import ada.cielo.prospects.model.entities.ProspectsQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProspectsQueueRepository  extends JpaRepository<ProspectsQueueEntity, Long> {

    @Query(value = "SELECT * FROM prospects_queue ORDER BY queueing_at ASC, id ASC LIMIT 1", nativeQuery = true)
    ProspectsQueueEntity getNext();

    @Query(value =  "SELECT prospects_queue.*" +
                    "FROM prospects_queue " +
                    "LEFT JOIN pre_registrations ON prospects_queue.pre_registration_id = pre_registrations.id " +
                    "LEFT JOIN mc_codes ON pre_registrations.mc_code_id = mc_codes.id " +
                    "WHERE" +
                    "    array_to_string(array[ " +
                    "        cast(prospects_queue.id as text), " +
                    "        cast(pre_registrations.id as text), " +
                    "        cast(pre_registrations.registration_type as text), " +
                    "        cast(pre_registrations.document_number as text), " +
                    "        cast(pre_registrations.email as text), " +
                    "        cast(pre_registrations.name as text), " +
                    "        cast(pre_registrations.attributes as text), " +
                    "        cast(mc_codes.id as text), " +
                    "        cast(mc_codes.code as text), " +
                    "        cast(mc_codes.program_type as text), " +
                    "        cast(pre_registrations.op as text), " +
                    "        cast(pre_registrations.at as text), " +
                    "        cast(prospects_queue.queueing_at as text) " +
                    "    ], '|') ILIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<ProspectsQueueEntity> findByTerm(String term);


    @Query(value = "SELECT * FROM prospects_queue WHERE pre_registration_id = ?1 ", nativeQuery = true)
    ProspectsQueueEntity findByPreRegistrationId(Long preRegistrationId);
}
