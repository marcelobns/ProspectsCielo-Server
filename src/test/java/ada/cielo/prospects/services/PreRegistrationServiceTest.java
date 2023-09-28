package ada.cielo.prospects.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ada.cielo.prospects.model.entities.MCCodeEntity;
import ada.cielo.prospects.model.entities.PreRegistrationEntity;
import ada.cielo.prospects.model.repositories.PreRegistrationRepository;
import ada.cielo.prospects.model.schemas.MCCodeSchema;
import ada.cielo.prospects.model.schemas.PreRegistrationSchema;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PreRegistrationServiceTest {

    @Mock
    private PreRegistrationRepository preRegistrationsRepository;

    @InjectMocks
    private PreRegistrationService preRegistrationService;

    @Test
    public void testSave() {
        MCCodeSchema mcCodeSchema = new MCCodeSchema();
        mcCodeSchema.setId(1L);
        mcCodeSchema.setCode("0001");
        mcCodeSchema.setProgramType("Name of programType 1");

        PreRegistrationSchema schema = new PreRegistrationSchema();

        schema.setId(1L);
        schema.setRegistrationType("pessoa_fisica");
        schema.setDocumentNumber("00000000000");
        schema.setName("Name of person 0");
        schema.setEmail("person_name@email.com");
        schema.setMcCode(mcCodeSchema);
        schema.setMcCodeId(mcCodeSchema.getId());
        schema.setAttributes("{'key': 'value'}");
        schema.setOp("Create");
        schema.setAt(LocalDateTime.now());

        PreRegistrationEntity entity = schema.toEntity();

        when(preRegistrationsRepository.save(any())).thenReturn(entity);

        PreRegistrationSchema result = preRegistrationService.save(schema);
        assertEquals(schema, result);
    }

    @Test
    public void testFindOne() {
        MCCodeSchema mcCodeSchema = new MCCodeSchema();
        mcCodeSchema.setId(1L);
        mcCodeSchema.setCode("0001");
        mcCodeSchema.setProgramType("Name of programType 1");

        Long id = 1L;
        PreRegistrationEntity entity = new PreRegistrationEntity(
                1L,
                "pessoa_fisica",
                "00000000000",
                "person_name@email.com",
                "Person Name",
                "{}",
                "Create",
                LocalDateTime.now(),
                mcCodeSchema.toEntity());

        when(preRegistrationsRepository.findById(id)).thenReturn(Optional.of(entity));

        PreRegistrationSchema result = preRegistrationService.findOne(id);
        assertEquals(entity.toSchema(), result);
    }

    @Test
    public void testFindByTerm() {
        List<PreRegistrationEntity> entities = Arrays.asList(
                new PreRegistrationEntity(
                        1L,
                        "pessoa_fisica",
                        "00000000001",
                        "email1@email.com",
                        "Name1 Surname1",
                        "{}",
                        "Create",
                        LocalDateTime.now(), new MCCodeEntity()),
                new PreRegistrationEntity(
                        2L,
                        "pessoa_fisica",
                        "00000000002",
                        "email2@email.com",
                        "Name2 Surname2",
                        "{}",
                        "Create",
                        LocalDateTime.now(),
                        new MCCodeEntity())
        );

        String term = "pessoa_fisica";
        when(preRegistrationsRepository.findByTerm(term)).thenReturn(entities);

        List<PreRegistrationSchema> result = preRegistrationService.findByTerm(term);
        assertEquals(2, result.size());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        PreRegistrationEntity entity = new PreRegistrationEntity(
                1L,
                "pessoa_fisica",
                "00000000000",
                "person_name@email.com",
                "Person Name",
                "{}",
                "Create",
                LocalDateTime.now(),
                new MCCodeEntity());

        when(preRegistrationsRepository.findById(id)).thenReturn(Optional.of(entity));

        PreRegistrationSchema result = preRegistrationService.delete(id);
        assertEquals(entity.toSchema(), result);

        verify(preRegistrationsRepository, times(1)).delete(entity);
    }
}
