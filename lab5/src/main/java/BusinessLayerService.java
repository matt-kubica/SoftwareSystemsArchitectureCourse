import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BusinessLayerService implements Service {


    private List <Record> records = null;

    public String getName() {
        return this.getClass().getName();
    }

    public void loadRecords() throws LoadingFailedException {
        try {
            DataAccessService service = (DataAccessService) ServiceLocator.getService("DataAccessService");
            this.records = service.getRecords();
            Logging.debug("Records loaded successfully...");
        } catch (ServiceNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException | InvalidDateFormatException e) {
            throw new LoadingFailedException(e);
        }
    }

    public List <Record> filterByCountry(String country) throws RecordsNotInitializedException {
        if (this.records == null) throw new RecordsNotInitializedException();
        return records.stream().filter(record -> record.location.equals(country)).collect(Collectors.toList());
    }

    public List <Record> filterByDate(Date begin, Date end) throws RecordsNotInitializedException {
        if (this.records == null) throw new RecordsNotInitializedException();
        return records.stream().filter(record ->
                record.date.after(begin) && record.date.before(end)
        ).collect(Collectors.toList());
    }

    public List <Record> getRecords() throws RecordsNotInitializedException {
        if (this.records == null) throw new RecordsNotInitializedException();
        return records;
    }
}
