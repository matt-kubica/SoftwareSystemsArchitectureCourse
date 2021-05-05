import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceCache {

    private List <Service> services = new ArrayList<Service>();

    public Service getService(String serviceName) {
        Optional <Service> o = this.services.stream().filter(service ->
                service.getName().equals(serviceName)).findFirst();
        if (o.isPresent()) {
            Logging.debug("Service " + serviceName + " found...");
            return o.get();
        } else {
            Logging.debug("Service " + serviceName + " not found...");
            return null;
        }

    }

    public void addService(Service newService) {
        this.services.add(newService);
        Logging.debug("Service " + newService.getName() + " added to cache...");
    }
}
