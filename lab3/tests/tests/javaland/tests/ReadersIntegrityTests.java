package javaland.tests;

import javaland.shipments.Shipment;
import javaland.shipments.ShipmentReader;
import javaland.shipments.TaxesCalculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public class ReadersIntegrityTests {

    public static List <TaxesCalculator> calculators;
    public static List <ShipmentReader> readers;
    public static final Double TOTAL_TAXES = 181280.00;

    @BeforeAll
    public static void initializeCalculators() {
        calculators = new ArrayList<>();
        ServiceLoader <TaxesCalculator> serviceLoader = ServiceLoader.load(TaxesCalculator.class);
        serviceLoader.forEach(taxesCalculator -> calculators.add(taxesCalculator));
    }

    @BeforeAll
    public static void initializeReaders() {
        readers = new ArrayList<>();
        ServiceLoader <ShipmentReader> serviceLoader = ServiceLoader.load(ShipmentReader.class);
        serviceLoader.forEach(shipmentReader -> readers.add(shipmentReader));
    }

    private List <Shipment> getShipmentsFromXMLFile() throws Exception {
        Optional <ShipmentReader> optional = readers.stream().filter(shipmentReader ->
                shipmentReader.getFileType().equals("xml")).findFirst();
        if (optional.isPresent())
            return optional.get().readFile("inputs/shipments.xml");
        else return null;
    }

    private List <Shipment> getShipmentsFromCSVFile() throws Exception {
        Optional <ShipmentReader> optional = readers.stream().filter(shipmentReader ->
                shipmentReader.getFileType().equals("csv")).findFirst();
        if (optional.isPresent())
            return optional.get().readFile("inputs/shipments.csv");
        else return null;
    }

    @Test
    public void calculateFromXMLFile() throws Exception {
        double sum = .0;
        List <Shipment> shipments = this.getShipmentsFromXMLFile();
        assertNotNull(shipments);
        shipments.forEach(Assertions::assertNotNull);

        Optional <TaxesCalculator> optional;
        for(Shipment shipment : shipments) {
            optional = calculators.stream().filter(taxesCalculator ->
                    taxesCalculator.getCountry().toLowerCase().equals(shipment.country.toLowerCase())).findFirst();
            assertTrue(optional.isPresent());
            sum += optional.get().calculateTax(shipment);
        }
        assertEquals(sum, TOTAL_TAXES);
    }

    @Test
    public void calculateFromCSVFile() throws Exception {
        double sum = .0;
        List <Shipment> shipments = this.getShipmentsFromCSVFile();
        assertNotNull(shipments);
        shipments.forEach(Assertions::assertNotNull);

        Optional <TaxesCalculator> optional;
        for(Shipment shipment : shipments) {
            optional = calculators.stream().filter(taxesCalculator ->
                    taxesCalculator.getCountry().toLowerCase().equals(shipment.country.toLowerCase())).findFirst();
            assertTrue(optional.isPresent());
            sum += optional.get().calculateTax(shipment);
        }
        assertEquals(sum, TOTAL_TAXES);
    }


}

