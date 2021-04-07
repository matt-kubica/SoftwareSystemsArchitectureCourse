package javaland.processor;

import javaland.shipments.Shipment;
import javaland.shipments.ShipmentReader;
import javaland.shipments.TaxesCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public class ShipmentFileProcessor {

    public String filename;
    private List <ShipmentReader> readers;
    private List <TaxesCalculator> calculators;

    public ShipmentFileProcessor(String filename) {
        this.filename = filename;
        this.initializeCalculators();
        this.initializeReaders();
    }

    private void initializeReaders() {
        ServiceLoader <ShipmentReader> loader = ServiceLoader.load(ShipmentReader.class);
        this.readers = new ArrayList<>();
        loader.forEach(shipmentReader -> readers.add(shipmentReader));
    }

    private void initializeCalculators() {
        ServiceLoader <TaxesCalculator> loader = ServiceLoader.load(TaxesCalculator.class);
        this.calculators = new ArrayList<>();
        loader.forEach(taxesCalculator -> calculators.add(taxesCalculator));
    }

    private List <Shipment> getShipments() throws Exception {
        ShipmentReader reader = null;
        Optional <ShipmentReader> optional;

        if (filename.endsWith(".xml")) {
            optional = this.readers.stream().filter(shipmentReader -> shipmentReader.getFileType().equals("xml")).findFirst();
            if (optional.isPresent()) reader = optional.get();
            else throw new Exception("XMLReader instance not found!");
        } else if (filename.endsWith(".csv")) {
            optional = this.readers.stream().filter(shipmentReader -> shipmentReader.getFileType().equals("csv")).findFirst();
            if (optional.isPresent()) reader = optional.get();
            else throw new Exception("CSVReader not found!");
        } else throw new Exception("Cannot find suitable reader!");

        return reader.readFile(filename);
    }

    public double calculate() throws Exception {
        double sum = 0;
        List <Shipment> shipments = this.getShipments();
        Optional <TaxesCalculator> optional;
        for(Shipment sh : shipments) {
            optional = this.calculators.stream().filter(taxesCalculator ->
                    taxesCalculator.getCountry().toLowerCase().equals(sh.country.toLowerCase())).findFirst();
            if (optional.isPresent())
                sum += optional.get().calculateTax(sh);
            else System.err.println("Cannot find TaxesCalculator for " + sh.country);
        }
        return sum;
    }

    public static final String FILENAME = "inputs/shipments.csv";

    public static void main(String[] args) {
        ShipmentFileProcessor processor = new ShipmentFileProcessor(FILENAME);
//        processor.calculators.forEach(taxesCalculator -> System.out.println(taxesCalculator.getCountry()));
//        processor.readers.forEach(shipmentReader -> System.out.println(shipmentReader.getFileType()));

        try {
            System.out.println(processor.calculate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
