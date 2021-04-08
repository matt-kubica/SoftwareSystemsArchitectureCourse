package javaland.tests;

import javaland.shipments.Shipment;
import javaland.shipments.TaxesCalculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public class CalculatorsIntegrityTests {

    public static class ShipmentTaxTuple {
        Shipment shipment;
        Double tax;

        public ShipmentTaxTuple(Shipment shipment, Double tax) {
            this.shipment = shipment;
            this.tax = tax;
        }
    }

    public static List <TaxesCalculator> calculators;
    public static List <ShipmentTaxTuple> shipmentsAndTaxes;

    @BeforeAll
    public static void initializeCalculators() {
        calculators = new ArrayList<>();
        ServiceLoader <TaxesCalculator> serviceLoader = ServiceLoader.load(TaxesCalculator.class);
        serviceLoader.forEach(taxesCalculator -> calculators.add(taxesCalculator));
    }

    @BeforeAll
    public static void createMockShipments() {
        shipmentsAndTaxes = new ArrayList<>();
        shipmentsAndTaxes.add(new ShipmentTaxTuple(new Shipment("coffee", "FOOD", 1000, 5.0, 500.0, "Brazil"), 2200.0));
        shipmentsAndTaxes.add(new ShipmentTaxTuple(new Shipment("cellphone", "ELETRONICS", 70, 800.0, 1500.0, "China"), 16800.0));
        shipmentsAndTaxes.add(new ShipmentTaxTuple(new Shipment("board game", "TOY", 3000, 100.0, 2000.0, "China"), 30000.0));
        shipmentsAndTaxes.add(new ShipmentTaxTuple(new Shipment("wine", "FOOD", 500, 500.0, 2000.0, "Italy"), 75000.0));
        shipmentsAndTaxes.add(new ShipmentTaxTuple(new Shipment("orange juice", "FOOD", 10000, 3.0, 700.0, "Brazil"), 12280.0));
        shipmentsAndTaxes.add(new ShipmentTaxTuple(new Shipment("tshirts", "CLOTHES", 5000, 30.0, 700.0, "Italy"), 45000.0));
    }

    public void testCalculators(String country) {
        assertNotNull(calculators);
        Optional <TaxesCalculator> o = calculators.stream().filter(taxesCalculator ->
                taxesCalculator.getCountry().equals(country)).findFirst();

        assertTrue(o.isPresent());
        TaxesCalculator calculator = o.get();
        assertEquals(calculator.getCountry(), country);
        shipmentsAndTaxes.forEach(shipmentTaxTuple -> {
            if (shipmentTaxTuple.shipment.country.toLowerCase().equals(country))
                assertEquals(calculator.calculateTax(shipmentTaxTuple.shipment), shipmentTaxTuple.tax, 1.0);
        });
    }

    @Test
    @DisplayName("Calculator test for China")
    public void testChinaCalculator() {
        this.testCalculators("china");
    }

    @Test
    @DisplayName("Calculator test for Italy")
    public void testItalyCalculator() {
        this.testCalculators("italy");
    }

    @Test
    @DisplayName("Calculator test for Brazil")
    public void testBrazilCalculator() {
        this.testCalculators("brazil");
    }

}
