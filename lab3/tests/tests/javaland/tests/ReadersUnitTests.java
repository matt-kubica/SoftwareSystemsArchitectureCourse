package javaland.tests;

import javaland.readers.CSVReader;
import javaland.readers.XMLReader;
import javaland.shipments.Shipment;
import javaland.shipments.ShipmentReader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public class ReadersUnitTests {

    public static List <ShipmentReader> readers;

    @BeforeEach
    public void initializeReaders() {
        readers = new ArrayList<>();
        ServiceLoader<ShipmentReader> loader = ServiceLoader.load(ShipmentReader.class);
        loader.forEach(readers::add);
    }

    @Test
    public void testReaders() {
        readers.forEach(Assertions::assertNotNull);
    }

    @Test
    public void testGetXMLReader() {
        Optional <ShipmentReader> o = readers.stream().filter(shipmentReader ->
                shipmentReader.getFileType().equals("xml")).findFirst();
        assertTrue(o.isPresent());
        ShipmentReader reader = o.get();
        assertNotNull(reader);
        assertTrue(reader instanceof XMLReader);
        assertEquals("xml", reader.getFileType());
    }

    @Test
    public void testGetCSVReader() {
        Optional <ShipmentReader> o = readers.stream().filter(shipmentReader ->
                shipmentReader.getFileType().equals("csv")).findFirst();
        assertTrue(o.isPresent());
        ShipmentReader reader = o.get();
        assertNotNull(reader);
        assertTrue(reader instanceof CSVReader);
        assertEquals("csv", reader.getFileType());
    }

    @Test
    public void testReadXMLFile() throws Exception {
        ShipmentReader reader = readers.stream().filter(shipmentReader ->
                shipmentReader.getFileType().equals("xml")).findFirst().get();
        List <Shipment> shipments = reader.readFile("inputs/shipments.xml");
        assertNotNull(shipments);
        shipments.forEach(Assertions::assertNotNull);
    }

    @Test
    public void testReadCSVFile() throws Exception {
        ShipmentReader reader = readers.stream().filter(shipmentReader ->
                shipmentReader.getFileType().equals("csv")).findFirst().get();
        List <Shipment> shipments = reader.readFile("inputs/shipments.csv");
        assertNotNull(shipments);
        shipments.forEach(Assertions::assertNotNull);
    }
}
