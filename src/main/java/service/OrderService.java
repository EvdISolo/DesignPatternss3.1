package service;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {private final List<Order> orders = new ArrayList<>();

    public void placeOrder(Order order) {
        orders.add(order);
        System.out.println("Заказ успешно оформлен!");
    }

    public List<Order> getOrders() {
        return orders;
    }
}


