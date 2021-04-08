module tests {
    uses javaland.shipments.ShipmentReader;
    uses javaland.shipments.TaxesCalculator;
    requires shipments;
    requires readers;
    requires taxes;

    requires junit;
    requires org.junit.jupiter.api;
    exports javaland.tests to org.junit.platform.commons;
}