package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class ECommerceDemo {
    public static void main(String[] args) {
        // Ініціалізація ECommercePlatform
        ECommercePlatform eCommercePlatform = new ECommercePlatform();

        // Додавання користувачів
        User user1 = new User(1, "john_doe");
        User user2 = new User(2, "jane_smith");

        eCommercePlatform.addUser(user1);
        eCommercePlatform.addUser(user2);

        // Додавання товарів
        Product product1 = new Product(101, "Laptop", 1200.0, 10);
        Product product2 = new Product(102, "Smartphone", 800.0, 15);

        eCommercePlatform.addProduct(product1);
        eCommercePlatform.addProduct(product2);

        // Симуляція взаємодії користувачів із кошиком
        user1.addToCart(product1, 2);
        user1.addToCart(product2, 1);

        user2.addToCart(product1, 1);
        user2.addToCart(product2, 3);

        // Виведення кошиків користувачів
        System.out.println("User 1 Cart: " + user1.getCart());
        System.out.println("User 2 Cart: " + user2.getCart());

        // Симуляція створення та обробки замовлень
        Map<Product, Integer> orderDetails1 = new HashMap<>();
        orderDetails1.put(product1, 2);
        orderDetails1.put(product2, 1);
        eCommercePlatform.createOrder(user1.getId(), orderDetails1);

        Map<Product, Integer> orderDetails2 = new HashMap<>();
        orderDetails2.put(product1, 1);
        orderDetails2.put(product2, 3);
        eCommercePlatform.createOrder(user2.getId(), orderDetails2);

        // Виведення замовлень
        System.out.println("List of Orders: " + eCommercePlatform.listOrders());
        System.out.println("List of Available Products: " + eCommercePlatform.listAvailableProducts());

        // Виклик функцій для відображення та фільтрації товарів
        displaySortedProductsByName(eCommercePlatform.getProducts());
        displaySortedProductsByPrice(eCommercePlatform.getProducts());
        displaySortedProductsByStock(eCommercePlatform.getProducts());
        filterProductsByAvailability(eCommercePlatform.getProducts(), 5);
    }

    // Функції для відображення та фільтрації товарів

    public static void displaySortedProductsByName(List<Product> products) {
        List<Product> sortedProducts = products.stream()
                .sorted(new ProductNameComparator())
                .collect(Collectors.toList());

        System.out.println("Sorted Products by Name:");
        sortedProducts.forEach(System.out::println);
    }

    public static void displaySortedProductsByPrice(List<Product> products) {
        List<Product> sortedProducts = products.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted Products by Price:");
        sortedProducts.forEach(System.out::println);
    }

    public static void displaySortedProductsByStock(List<Product> products) {
        List<Product> sortedProducts = products.stream()
                .sorted(new ProductStockComparator())
                .collect(Collectors.toList());

        System.out.println("Sorted Products by Stock:");
        sortedProducts.forEach(System.out::println);
    }

    public static void filterProductsByAvailability(List<Product> products, int minStock) {
        List<Product> availableProducts = products.stream()
                .filter(product -> product.getStock() >= minStock)
                .collect(Collectors.toList());

        System.out.println("Products with Stock >= " + minStock + ":");
        availableProducts.forEach(System.out::println);
    }
}
