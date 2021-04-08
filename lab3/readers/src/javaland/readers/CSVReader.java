package javaland.readers;

import javaland.shipments.Shipment;
import javaland.shipments.ShipmentReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader implements ShipmentReader {

    @Override
    public List<Shipment> readFile(String path) throws Exception {
        List <Shipment> result = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(path));
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(";");

                String productName = line[0];
                String productType = line[1];
                Integer amount = Integer.parseInt(line[2]);
                Double individualPrice = Double.parseDouble(line[3]);
                Double shipmentPrice = Double.parseDouble(line[4]);
                String country = line[5];

                result.add(new Shipment(productName, productType, amount, individualPrice, shipmentPrice, country));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // this is really simplified, normally there some custom exceptions should be implemented
            // or exceptions should be passed further
            throw new Exception("Cannot read CSV file");
        }
        return result;
    }

    @Override
    public String getFileType() {
        return "csv";
    }
}
