import javaland.shipments.TaxesCalculator;
import javaland.taxes.BrazilTaxes;
import javaland.taxes.ChinaTaxes;
import javaland.taxes.ItalyTaxes;

module taxes {
    requires shipments;
    provides TaxesCalculator with ItalyTaxes, BrazilTaxes, ChinaTaxes;
}