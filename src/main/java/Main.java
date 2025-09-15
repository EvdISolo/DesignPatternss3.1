import filter.KeywordFilter;
import filter.PriceRangeFilter;
import model.Order;
import model.Product;
import service.OrderService;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();

        // Создаем некоторые товары
        productService.addProduct(new Product(1, "Телевизор", "Samsung", 50000, 4.5));
        productService.addProduct(new Product(2, "Ноутбук", "Dell", 70000, 4.7));
        productService.addProduct(new Product(3, "Телефон", "Apple", 100000, 4.8));
        productService.addProduct(new Product(4, "Наушники", "Sony", 15000, 4.3));
        productService.addProduct(new Product(5, "Клавиатура", "Logitech", 3000, 4.2));

        List<Product> cart = new ArrayList<>();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1. Просмотреть все товары");
            System.out.println("2. Фильтровать товары по цене");
            System.out.println("3. Искать товары по ключевому слову");
            System.out.println("4. Добавить товар в корзину");
            System.out.println("5. Посмотреть корзину");
            System.out.println("6. Оформить заказ");
            System.out.println("7. Выйти");
            System.out.print("Выберите пункт: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Product> allProducts = productService.getAllProducts();
                    System.out.println("\nВсе товары:");
                    for (Product p : allProducts) {
                        System.out.println(p.getId() + ". " + p.getName() + " - " + p.getManufacturer() + " - " + p.getPrice() + " руб.");
                    }
                    break;
                case 2:
                    System.out.print("Введите минимальную цену: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Введите максимальную цену: ");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine();

                    PriceRangeFilter priceFilter = new PriceRangeFilter(minPrice, maxPrice);
                    List<Product> filteredByPrice = productService.getFilteredProducts(priceFilter);
                    System.out.println("\nТовары по цене:");
                    for (Product p : filteredByPrice) {
                        System.out.println(p.getId() + ". " + p.getName() + " - " + p.getPrice() + " руб.");
                    }
                    break;
                case 3:
                    System.out.print("Введите ключевое слово: ");
                    String keyword = scanner.nextLine();
                    KeywordFilter keywordFilter = new KeywordFilter(keyword);
                    List<Product> filteredByKeyword = productService.getFilteredProducts(keywordFilter);
                    System.out.println("\nТовары по ключевому слову:");
                    for (Product p : filteredByKeyword) {
                        System.out.println(p.getId() + ". " + p.getName() + " - " + p.getManufacturer());
                    }
                    break;
                case 4:
                    System.out.print("Введите ID товара для добавления в корзину: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Product> productOpt = productService.getProductById(productId);
                    if (productOpt.isPresent()) {
                        cart.add(productOpt.get());
                        System.out.println("Товар добавлен в корзину.");
                    } else {
                        System.out.println("Товар не найден.");
                    }
                    break;
                case 5:
                    System.out.println("\nКорзина:");
                    if (cart.isEmpty()) {
                        System.out.println("Пусто");
                    } else {
                        for (int i = 0; i < cart.size(); i++) {
                            Product p = cart.get(i);
                            System.out.println((i + 1) + ". " + p.getName() + " - " + p.getPrice() + " руб.");
                        }
                    }
                    break;
                case 6:
                    if (cart.isEmpty()) {
                        System.out.println("Корзина пуста.");
                    } else {
                        Order order = new Order(cart);
                        orderService.placeOrder(order);
                        System.out.println("Заказ оформлен. Общая сумма: " + order.getTotalPrice() + " руб.");
                        cart.clear();
                    }
                    break;
                case 7:
                    exit = true;
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Некорректный выбор");
            }
        }

        scanner.close();
    }
}
