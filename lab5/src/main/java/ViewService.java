import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class ViewService implements Service {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public String getName() {
        return this.getClass().getName();
    }

    public void printBasicInfo(Record record) {
        System.out.println(String.format("Date: %s", formatter.format(record.date)));
        System.out.println(String.format("Location: %s", record.location));
        System.out.println(String.format("New cases: %d", record.newCases));
        System.out.println(String.format("New deaths: %d\n", record.newDeaths));
    }

    public void printFullInfo(Record record) {
        Field[] fields = Record.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                System.out.println(String.format("%s: %s", field.getName(), field.get(record)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n");
    }
}
