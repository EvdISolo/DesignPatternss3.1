package filter;

import model.Product;

public interface ProductFilter {
    boolean matches(Product product);
}