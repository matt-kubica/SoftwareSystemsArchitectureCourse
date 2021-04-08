package javaland.shipments;

import java.util.List;

public interface ShipmentReader {
    List <Shipment> readFile(String path) throws Exception;
    String getFileType();
}
