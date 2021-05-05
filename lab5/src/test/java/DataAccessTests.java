import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.Assert.*;

public class DataAccessTests {

    DataAccessService dataAccessService;

    @BeforeEach
    public void loadService() throws ServiceNotFoundException {
        dataAccessService = (DataAccessService) ServiceLocator.getService("DataAccessService");
    }


    @Test
    public void testGetRecords() throws IOException, InvalidDateFormatException, ServiceNotFoundException {
        assertNotNull(dataAccessService);
        assertEquals(dataAccessService.getRecords().size(), 59354);
    }
}
