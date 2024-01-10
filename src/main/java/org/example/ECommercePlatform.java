package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ECommercePlatform {
    private Map<Integer, User> users;
    private Map<Integer, Product> products;
    private Map<Integer, Order> orders;

    public ECommercePlatform() {
        this.users = new HashMap<>();
        this.products = new HashMap<>();
        this.orders = new HashMap<>();
    }

    // Методи для додавання користувачів, товарів та замовлень

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void createOrder(Integer userId, Map<Product, Integer> orderDetails) {
        User user = users.get(userId);
        if (user != null) {
            Order order = new Order(generateOrderId(), userId, orderDetails);
            orders.put(order.getId(), order);

            // Оновлення запасів товарів
            for (Map.Entry<Product, Integer> entry : orderDetails.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                int remainingStock = product.getStock() - quantity;
                product.setStock(remainingStock);
            }
        }
    }
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }
    // Методи для переліку доступних товарів, користувачів та замовлень

    public Map<Integer, Product> listAvailableProducts() {
        return products;
    }

    public Map<Integer, User> listUsers() {
        return users;
    }

    public Map<Integer, Order> listOrders() {
        return orders;
    }

    // Оновлення запасів товарів

    public void updateProductStock(Integer productId, int newStock) {
        Product product = products.get(productId);
        if (product != null) {
            product.setStock(newStock);
        }
    }

    // Допоміжний метод для генерації унікального ідентифікатора для замовлення

    private int generateOrderId() {
        // Логіка генерації ідентифікатора може бути додатково вдосконалена
        return orders.size() + 1;

    }
        // Функція для рекомендації товарів користувачам

        public List<Product> recommendProducts(User user) {
            List<Product> recommendedProducts = new ArrayList<>();

            // Отримання списку товарів з кошика користувача
            Map<Product, Integer> userCart = user.getCart();

            // Додавання товарів з кошика користувача до рекомендованих товарів
            for (Map.Entry<Product, Integer> entry : userCart.entrySet()) {
                recommendedProducts.add(entry.getKey());
            }

            // TODO: Додаткова логіка для рекомендацій на основі історії замовлень або інших факторів

            System.out.println("Recommended Products for " + user.getUsername() + ":");
            recommendedProducts.forEach(System.out::println);

            return recommendedProducts;
    }
}