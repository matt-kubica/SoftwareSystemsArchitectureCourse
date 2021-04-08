import javaland.readers.CSVReader;
import javaland.readers.XMLReader;
import javaland.shipments.ShipmentReader;

module readers {
    requires shipments;
    requires java.xml;
    provides ShipmentReader with CSVReader, XMLReader;

    // for test purposes
    exports javaland.readers to tests;
}