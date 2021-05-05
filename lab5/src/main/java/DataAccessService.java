import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessService implements Service {

    public static final String URL = "https://covid.ourworldindata.org/data/ecdc/full_data.csv";

    public String getName() {
        return this.getClass().getName();
    }

    public List <Record> getRecords() throws IOException, InvalidDateFormatException {
        List <Record> result = new ArrayList<>();
        BufferedInputStream input = new BufferedInputStream(new URL(URL).openStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        Logging.debug("Stream opened successfully...");

        // skip first line
        reader.readLine();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                result.add(Record.fromLine(line, ","));
            }
        } catch (ParseException e) {
            Logging.error("Invalid date...");
            throw new InvalidDateFormatException(e);
        }


        return result;
    }
}
