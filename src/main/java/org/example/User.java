package org.example;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Integer id;
    private String username;
    private Map<Product, Integer> cart;

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
        this.cart = new HashMap<>();
    }
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }
    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void addToCart(Product product, int quantity) {
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        if (cart.containsKey(product)) {
            int currentQuantity = cart.get(product);
            if (currentQuantity - quantity <= 0) {
                cart.remove(product);
            } else {
                cart.put(product, currentQuantity - quantity);
            }
        }
    }

    public void modifyCart(Product product, int newQuantity) {
        if (cart.containsKey(product) && newQuantity > 0) {
            cart.put(product, newQuantity);
        }
    }
}