package javaland.tests;

import javaland.shipments.Shipment;
import javaland.shipments.ShipmentReader;
import javaland.shipments.TaxesCalculator;

import static org.junit.jupiter.api.Assertions.*;

import javaland.taxes.BrazilTaxes;
import javaland.taxes.ChinaTaxes;
import javaland.taxes.ItalyTaxes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public class CalculatorsUnitTests {

    public static List <TaxesCalculator> calculators;

    @BeforeEach
    public void initializeCalculators() {
        calculators = new ArrayList<>();
        ServiceLoader <TaxesCalculator> loader = ServiceLoader.load(TaxesCalculator.class);
        loader.forEach(calculators::add);
    }

    @Test
    public void testCalculators() {
        calculators.forEach(Assertions::assertNotNull);
    }

    @Test
    public void testGetItalyTaxesCalculator() {
        Optional <TaxesCalculator> o = calculators.stream().filter(taxesCalculator ->
                taxesCalculator.getCountry().equals("italy")).findFirst();
        assertTrue(o.isPresent());
        TaxesCalculator calculator = o.get();
        assertNotNull(calculator);
        assertTrue(calculator instanceof ItalyTaxes);
        assertEquals("italy", calculator.getCountry());
    }

    @Test
    public void testGetBrazilTaxesCalculator() {
        Optional <TaxesCalculator> o = calculators.stream().filter(taxesCalculator ->
                taxesCalculator.getCountry().equals("brazil")).findFirst();
        assertTrue(o.isPresent());
        TaxesCalculator calculator = o.get();
        assertNotNull(calculator);
        assertTrue(calculator instanceof BrazilTaxes);
        assertEquals("brazil", calculator.getCountry());
    }

    @Test
    public void testGetChinaTaxesCalculator() {
        Optional <TaxesCalculator> o = calculators.stream().filter(taxesCalculator ->
                taxesCalculator.getCountry().equals("china")).findFirst();
        assertTrue(o.isPresent());
        TaxesCalculator calculator = o.get();
        assertNotNull(calculator);
        assertTrue(calculator instanceof ChinaTaxes);
        assertEquals("china", calculator.getCountry());
    }

    @Test
    public void testCalculateBrazilTax() {
        TaxesCalculator calculator = calculators.stream().filter(taxesCalculator ->
                taxesCalculator.getCountry().equals("brazil")).findFirst().get();
        assertNotNull(calculator);
        assertEquals(calculator.calculateTax(new Shipment("coffee", "FOOD", 1000, 5.0, 500.0, "Brazil")), 2200.0);
    }

    @Test
    public void testCalculateChinaTax() {
        TaxesCalculator calculator = calculators.stream().filter(taxesCalculator ->
                taxesCalculator.getCountry().equals("china")).findFirst().get();
        assertNotNull(calculator);
        assertEquals(calculator.calculateTax(new Shipment("cellphone", "ELETRONICS", 70, 800.0, 1500.0, "China")), 16800.0);
    }

    @Test
    public void testCalculateItalyTax() {
        TaxesCalculator calculator = calculators.stream().filter(taxesCalculator ->
                taxesCalculator.getCountry().equals("italy")).findFirst().get();
        assertNotNull(calculator);
        assertEquals(calculator.calculateTax(new Shipment("tshirts", "CLOTHES", 5000, 30.0, 700.0, "Italy")), 45000.0);
    }
}
