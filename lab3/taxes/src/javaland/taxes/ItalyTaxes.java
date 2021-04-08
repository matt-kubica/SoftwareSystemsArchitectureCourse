package javaland.taxes;

import javaland.shipments.Shipment;
import javaland.shipments.TaxesCalculator;

public class ItalyTaxes implements TaxesCalculator {

    @Override
    public double calculateTax(Shipment shipment) {
        double fixedCost = 0;

        if(shipment.individualPrice > 500) fixedCost = 5;

        double total = shipment.amount * (shipment.individualPrice + fixedCost);
        double tax = 0.3 * total;
        if(shipment.productType.equals("CLOTHES") && total < 50000) {
            tax -= (tax * 0.2);
        }
        return tax;
    }

    @Override
    public String getCountry() {
        return "italy";
    }
}
