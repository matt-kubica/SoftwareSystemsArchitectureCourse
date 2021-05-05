

public class ServiceLocator {

    private static ServiceCache serviceCache = new ServiceCache();

    public static Service getService(String serviceName) throws ServiceNotFoundException {
        Service service = serviceCache.getService(serviceName);

        if (service != null) return service;

        if (serviceName.equals(DataAccessService.class.getName()))
            service = new DataAccessService();
        else if (serviceName.equals(BusinessLayerService.class.getName()))
            service = new BusinessLayerService();
        else if (serviceName.equals(ControllerService.class.getName()))
            service = new ControllerService();
        else if (serviceName.equals(ViewService.class.getName()))
            service = new ViewService();
        else throw new ServiceNotFoundException();

        serviceCache.addService(service);
        return service;
    }
}
