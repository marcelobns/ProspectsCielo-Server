package ada.cielo.prospects.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import ada.cielo.prospects.model.entities.MCCodeEntity;
import ada.cielo.prospects.model.repositories.MCCodeRepository;
import ada.cielo.prospects.model.schemas.MCCodeSchema;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MCCodeServiceTest {

    @Mock
    private MCCodeRepository mcCodeRepository;

    @InjectMocks
    private MCCodeService mcCodeService;

    @Test
    public void testSave() {
        MCCodeSchema mcCodeSchema = new MCCodeSchema();
        mcCodeSchema.setCode("0000");
        mcCodeSchema.setProgramType("Name of programType 0");

        mcCodeService.save(mcCodeSchema);

        verify(mcCodeRepository, times(1)).save(any(MCCodeEntity.class));
    }

    @Test
    public void testFindAll() {
        List<MCCodeEntity> mcCodeEntities = Arrays.asList(
                new MCCodeEntity("0001", "Name of programType 1"),
                new MCCodeEntity("0002", "Name of programType 2"),
                new MCCodeEntity("0003", "Name of programType 3"),
                new MCCodeEntity("0004", "Name of programType 4")
        );

        when(mcCodeRepository.findAll()).thenReturn(mcCodeEntities);

        List<MCCodeSchema> result = mcCodeService.findAll();
        assertEquals(4, result.size());
    }

    @Test
    public void testFindByTerm() {
        List<MCCodeEntity> mcCodeEntities = Arrays.asList(
                new MCCodeEntity("0005", "Name of programType 5")
        );
        String term = "0005";
        when(mcCodeRepository.findByTerm(term)).thenReturn(mcCodeEntities);
        List<MCCodeSchema> result = mcCodeService.findByTerm(term);
        assertEquals(1, result.size());
    }
}
