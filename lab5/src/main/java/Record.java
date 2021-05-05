import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {

    public Date date;
    public String location;

    public Integer newCases = 0;
    public Integer newDeaths = 0;
    public Integer totalCases = 0;
    public Integer totalDeaths = 0;

    public Double weeklyCases = 0.0;
    public Double weeklyDeaths = 0.0;
    public Double biweeklyCases = 0.0;
    public Double biweeklyDeaths = 0.0;

    public Record() {}

    public Record(Date date, String location,
                   Integer newCases, Integer newDeaths, Integer totalCases, Integer totalDeaths,
                   Double weeklyCases, Double weeklyDeaths, Double biweeklyCases, Double biweeklyDeaths) {
        this.date = date;
        this.location = location;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.weeklyCases = weeklyCases;
        this.weeklyDeaths = weeklyDeaths;
        this.biweeklyCases = biweeklyCases;
        this.biweeklyDeaths = biweeklyDeaths;
    }

    private static Integer parseInteger(String text) {
        return !text.equals("") ? Integer.parseInt(text) : 0;
    }

    private static Double parseDouble(String text) {
        return !text.equals("") ? Double.parseDouble(text) : 0.0;
    }

    public static Record fromLine(String line, String delimiter) throws ParseException {
        String[] parts = line.split(delimiter, -1);
        Record record = new Record();
        record.date = new SimpleDateFormat("yyyy-MM-dd").parse(parts[0]);
        record.location = parts[1];

        record.newCases = Record.parseInteger(parts[2]);
        record.newDeaths = Record.parseInteger(parts[3]);
        record.totalCases = Record.parseInteger(parts[4]);
        record.totalDeaths = Record.parseInteger(parts[5]);

        record.weeklyCases = Record.parseDouble(parts[6]);
        record.weeklyDeaths = Record.parseDouble(parts[7]);
        record.biweeklyCases = Record.parseDouble(parts[8]);
        record.biweeklyDeaths = Record.parseDouble(parts[9]);

        return record;
    }

    public String toString() {
        return String.format("%s, %s: %d", this.location, this.date.toString(), this.newCases);
    }
}
