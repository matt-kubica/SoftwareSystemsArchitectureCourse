package javaland.shipments;

public class Shipment {

    public String productName;
    public String productType;
    public Integer amount;
    public Double individualPrice;
    public Double shipmentPrice;
    public String country;

    public Shipment(String productName, String productType, Integer amount, Double individualPrice, Double shipmentPrice, String country) {
        this.productName = productName;
        this.productType = productType;
        this.amount = amount;
        this.individualPrice = individualPrice;
        this.shipmentPrice = shipmentPrice;
        this.country = country;
    }
}
