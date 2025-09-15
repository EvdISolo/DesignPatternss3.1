package model;

public class Product {
    private final int id;
    private final String name;
    private final String manufacturer;
    private final double price;
    private double rating;

    public Product(int id, String name, String manufacturer, double price, double rating) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getManufacturer() { return manufacturer; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}

