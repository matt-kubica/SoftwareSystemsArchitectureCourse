import java.io.IOException;

public class Main {


    public static void main(String[] args) throws ServiceNotFoundException, RecordsNotInitializedException, LoadingFailedException {
        Logging.setLevel(LoggingLevel.DEBUG);

        ControllerService controllerService = (ControllerService) ServiceLocator.getService("ControllerService");
        controllerService.run();

    }
}
