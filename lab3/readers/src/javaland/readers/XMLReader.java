package javaland.readers;

import javaland.shipments.Shipment;
import javaland.shipments.ShipmentReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLReader implements ShipmentReader {

    @Override
    public List<Shipment> readFile(String path) {
        List <Shipment> result = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(path));
            NodeList list = doc.getElementsByTagName("shipment");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String productName = element.getAttribute("product");
                    String productType = element.getAttribute("type");
                    Integer amount = Integer.parseInt(element.getAttribute("amount"));
                    Double individualPrice = Double.parseDouble(element.getAttribute("individualPrice"));
                    Double shipmentPrice = Double.parseDouble(element.getAttribute("shipmentPrice"));
                    String country = element.getAttribute("country");

                    result.add(new Shipment(productName, productType, amount, individualPrice, shipmentPrice, country));
                }
            }

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getFileType() {
        return "xml";
    }
}
