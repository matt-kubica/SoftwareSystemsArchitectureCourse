package javaland.taxes;

import javaland.shipments.Shipment;
import javaland.shipments.TaxesCalculator;

public class ChinaTaxes implements TaxesCalculator {

    @Override
    public double calculateTax(Shipment shipment) {
        double taxPercentage = 0.3;
        if(shipment.productType.equals("ELECTRONICS")) taxPercentage = 0.5;
        if(shipment.amount > 1000 || shipment.amount * shipment.individualPrice > 100000) taxPercentage -= 0.2;
        return taxPercentage * shipment.individualPrice * shipment.amount;
    }

    @Override
    public String getCountry() {
        return "china";
    }
}
