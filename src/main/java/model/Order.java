package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Product> products;
    private final double totalPrice;

    public Order(List<Product> products) {
        this.products = new ArrayList<>(products);
        this.totalPrice = calculateTotal();
    }

    private double calculateTotal() {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public List<Product> getProducts() { return products; }
    public double getTotalPrice() { return totalPrice; }
}
