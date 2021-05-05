import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BusinessLayerTests {

    public BusinessLayerService businessLayerService;

    @BeforeEach
    public void loadService() throws ServiceNotFoundException {
        businessLayerService = (BusinessLayerService) ServiceLocator.getService("BusinessLayerService");
    }

    @Test
    public void testLoadRecords() {
        assertThrows(RecordsNotInitializedException.class, () -> {
            businessLayerService.filterByCountry("SomeCountry");
        });
    }

    @Test
    public void filterByCountryTest() throws RecordsNotInitializedException, LoadingFailedException {
        businessLayerService.loadRecords();

        for (Record record : businessLayerService.filterByCountry("Poland")) {
            assertEquals(record.location, "Poland");
        }
    }

    @Test
    public void filterByDateTest() throws RecordsNotInitializedException, LoadingFailedException, ParseException {
        businessLayerService.loadRecords();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Record record : businessLayerService.filterByDate(formatter.parse("2020-05-18"), formatter.parse("2020-06-01"))) {
            assertTrue(record.date.after(formatter.parse("2020-05-18")));
            assertTrue(record.date.before(formatter.parse("2020-06-01")));
        }

    }

}
