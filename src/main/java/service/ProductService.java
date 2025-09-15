package service;

import filter.ProductFilter;
import model.Product;

import java.util.*;

public class ProductService {
    private final Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Optional<Product> getProductById(int id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public List<Product> getFilteredProducts(ProductFilter filter) {
        List<Product> result = new ArrayList<>();
        for (Product p : products.values()) {
            if (filter.matches(p)) {
                result.add(p);
            }
        }
        return result;
    }
}
