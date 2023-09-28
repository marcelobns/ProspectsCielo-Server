package ada.cielo.prospects.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import ada.cielo.prospects.model.entities.ProspectsQueueEntity;
import ada.cielo.prospects.model.repositories.ProspectsQueueRepository;
import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import ada.cielo.prospects.model.schemas.ProspectsQueueSchema;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProspectsQueueServiceTest {

    @Mock
    private ProspectsQueueRepository prospectsQueueRepository;

    @InjectMocks
    private ProspectsQueueService prospectsQueueService;

    @Test
    public void testSave() {
        PreRegistrationSchema preRegistrationSchema = new PreRegistrationSchema();
        preRegistrationSchema.setId(1L);
        preRegistrationSchema.setRegistrationType("pessoa_fisica");
        preRegistrationSchema.setDocumentNumber("00000000000");
        preRegistrationSchema.setName("Name of person 0");
        preRegistrationSchema.setEmail("person_name@email.com");
        preRegistrationSchema.setAttributes("{'key': 'value'}");
        preRegistrationSchema.setOp("Create");
        preRegistrationSchema.setAt(LocalDateTime.now());

        ProspectsQueueSchema schema = new ProspectsQueueSchema();
        schema.setId(1L);
        schema.setPreRegistration(preRegistrationSchema);
        schema.setPreRegistrationId(preRegistrationSchema.getId());
        schema.setQueueingAt(LocalDateTime.now());

        ProspectsQueueEntity entity = schema.toEntity();

        when(prospectsQueueRepository.save(any())).thenReturn(entity);

        ProspectsQueueSchema result = prospectsQueueService.save(schema);
        assertEquals(schema, result);
    }

    @Test
    public void testFindByTerm() {

    }

    @Test
    public void testGetNext() {

    }

    @Test
    public void testDelete() {

    }
}
