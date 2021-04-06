package javaland.shipments;

public interface TaxesCalculator {
    double calculateTax(Shipment shipment);
    String getCountry();
}
