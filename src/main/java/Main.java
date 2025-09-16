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


    public static class Config {
        public static final int MENU_VIEW_PRODUCTS = 1;
        public static final int MENU_FILTER_BY_PRICE = 2;
        public static final int MENU_FILTER_BY_KEYWORD = 3;
        public static final int MENU_ADD_TO_CART = 4;
        public static final int MENU_VIEW_CART = 5;
        public static final int MENU_PLACE_ORDER = 6;
        public static final int MENU_EXIT = 7;

        public static final double DEFAULT_MIN_PRICE = 0.0;
        public static final double DEFAULT_MAX_PRICE = Double.MAX_VALUE;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        List<Product> cart = new ArrayList<>();

        // Создаем некоторые товары
        productService.addProduct(new Product(1, "Телевизор", "Samsung", 50000, 4.5));
        productService.addProduct(new Product(2, "Ноутбук", "Dell", 70000, 4.7));
        productService.addProduct(new Product(3, "Телефон", "Apple", 100000, 4.8));
        productService.addProduct(new Product(4, "Наушники", "Sony", 15000, 4.3));
        productService.addProduct(new Product(5, "Клавиатура", "Logitech", 3000, 4.2));

        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case Config.MENU_VIEW_PRODUCTS:
                    printProducts(productService.getAllProducts());
                    break;
                case Config.MENU_FILTER_BY_PRICE:
                    handleFilterByPrice(scanner, productService);
                    break;
                case Config.MENU_FILTER_BY_KEYWORD:
                    handleFilterByKeyword(scanner, productService);
                    break;
                case Config.MENU_ADD_TO_CART:
                    handleAddToCart(scanner, productService, cart);
                    break;
                case Config.MENU_VIEW_CART:
                    printCart(cart);
                    break;
                case Config.MENU_PLACE_ORDER:
                    handlePlaceOrder(cart, orderService);
                    break;
                case Config.MENU_EXIT:
                    exit = true;
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Некорректный выбор");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println(Config.MENU_VIEW_PRODUCTS + ". Просмотреть все товары");
        System.out.println(Config.MENU_FILTER_BY_PRICE + ". Фильтровать товары по цене");
        System.out.println(Config.MENU_FILTER_BY_KEYWORD + ". Искать товары по ключевому слову");
        System.out.println(Config.MENU_ADD_TO_CART + ". Добавить товар в корзину");
        System.out.println(Config.MENU_VIEW_CART + ". Посмотреть корзину");
        System.out.println(Config.MENU_PLACE_ORDER + ". Оформить заказ");
        System.out.println(Config.MENU_EXIT + ". Выйти");
        System.out.print("Выберите пункт: ");
    }

    private static void printProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Нет подходящих товаров.");
            return;
        }
        for (Product p : products) {
            System.out.println(p.getId() + ". " + p.getName() + " - " + p.getManufacturer() + " - " + p.getPrice() + " руб.");
        }
    }

    private static void handleFilterByPrice(Scanner scanner, ProductService productService) {
        System.out.print("Введите минимальную цену: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Введите максимальную цену: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();

        PriceRangeFilter priceFilter = new PriceRangeFilter(minPrice, maxPrice);
        List<Product> filteredByPrice = productService.getFilteredProducts(priceFilter);
        printProducts(filteredByPrice);
    }

    private static void handleFilterByKeyword(Scanner scanner, ProductService productService) {
        System.out.print("Введите ключевое слово: ");
        String keyword = scanner.nextLine();
        KeywordFilter keywordFilter = new KeywordFilter(keyword);
        List<Product> filteredByKeyword = productService.getFilteredProducts(keywordFilter);
        printProducts(filteredByKeyword);
    }

    private static void handleAddToCart(Scanner scanner, ProductService productService, List<Product> cart) {
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
    }

    private static void printCart(List<Product> cart) {
        System.out.println("\nКорзина:");
        if (cart.isEmpty()) {
            System.out.println("Пусто");
        } else {
            for (int i = 0; i < cart.size(); i++) {
                Product p = cart.get(i);
                System.out.println((i + 1) + ". " + p.getName() + " - " + p.getPrice() + " руб.");
            }
        }
    }

    private static void handlePlaceOrder(List<Product> cart, OrderService orderService) {
        if (cart.isEmpty()) {
            System.out.println("Корзина пуста.");
        } else {
            Order order = new Order(cart);
            orderService.placeOrder(order);
            System.out.println("Заказ оформлен. Общая сумма: " + order.getTotalPrice() + " руб.");
            cart.clear();
        }
    }
}