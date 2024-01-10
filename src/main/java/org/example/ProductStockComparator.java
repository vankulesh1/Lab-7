package org.example;

import java.util.Comparator;

public class ProductStockComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return Integer.compare(product1.getStock(), product2.getStock());
    }
}