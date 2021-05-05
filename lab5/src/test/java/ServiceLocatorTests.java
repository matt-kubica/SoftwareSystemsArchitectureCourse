import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceLocatorTests {

    @Test
    public void getServices() throws ServiceNotFoundException {
        String[] serviceNames = { "DataAccessService", "BusinessLayerService", "ViewService", "ControllerService" };

        for (String sn : serviceNames) {
            Service service = ServiceLocator.getService(sn);
            assertEquals(service.getName(), sn);
        }
    }

    @Test(expected = ServiceNotFoundException.class)
    public void getNonExistentService() throws ServiceNotFoundException {
        ServiceLocator.getService("NonExistentService");
    }

    // TODO: something with caching etc...

}
