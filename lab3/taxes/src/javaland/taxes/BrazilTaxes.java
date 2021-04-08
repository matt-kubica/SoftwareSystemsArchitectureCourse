package javaland.taxes;

import javaland.shipments.Shipment;
import javaland.shipments.TaxesCalculator;

public class BrazilTaxes implements TaxesCalculator {

    @Override
    public double calculateTax(Shipment shipment) {
        double tax = 0.4 * (shipment.individualPrice * shipment.amount + shipment.shipmentPrice);
        if(shipment.productType.equals("FOOD") && shipment.amount > 10000) return tax - (tax * 0.4);
        else return tax;
    }

    @Override
    public String getCountry() {
        return "brazil";
    }
}
