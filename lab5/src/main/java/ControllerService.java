import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class ControllerService implements Service {

    private BusinessLayerService businessLayerService;
    private ViewService viewService;

    public String getName() {
        return this.getClass().getName();
    }

    public ControllerService() {

        try {
            this.businessLayerService = (BusinessLayerService) ServiceLocator.getService("BusinessLayerService");
            this.viewService = (ViewService) ServiceLocator.getService("ViewService");
        } catch (ServiceNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void run() throws LoadingFailedException, RecordsNotInitializedException {
        Scanner scanner = new Scanner(System.in);
        List <Record> result = null;
        Consumer <Record> displayFunction;
        boolean exit = false;
        while (!exit) {
            this.businessLayerService.loadRecords();
            System.out.println("\n\nDisplay option: ");
            System.out.println("\t1. Simple...");
            System.out.println("\t2. Extended...");
            System.out.print("Chosen: ");
            switch (scanner.nextInt()) {
                case 1:
                    displayFunction = record -> this.viewService.printBasicInfo(record);
                    break;
                case 2:
                    displayFunction = record -> this.viewService.printFullInfo(record);
                    break;
                default:
                    displayFunction = record -> this.viewService.printBasicInfo(record);
                    break;
            }

            System.out.println("\nRecord filtering options:");
            System.out.println("\t1. Filter by country...");
            System.out.println("\t2. Filter by date...");
            System.out.println("\t3. No filtering...");
            System.out.print("Chosen: ");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.print("Country: ");
                    String countryName = scanner.next();
                    result = this.businessLayerService.filterByCountry(countryName);
                    break;
                case 2:
                    try {
                        System.out.print("From (yyyy-MM-dd): ");
                        Date beginDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next());
                        System.out.print("To (yyyy-MM-dd): ");
                        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next());
                        result = this.businessLayerService.filterByDate(beginDate, endDate);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format!!");
                        continue;
                    }
                    break;
                case 3:
                    result = this.businessLayerService.getRecords();
                    break;
                default: exit = true; break;
            }
            if (!exit) {
                System.out.println("Displaying: ");
                result.forEach(displayFunction);
            }

        }
    }
}
